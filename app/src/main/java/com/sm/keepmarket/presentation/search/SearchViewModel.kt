package com.sm.keepmarket.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.IHighlightRepository
import com.sm.keepmarket.domain.model.SearchItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class SearchViewModel(private val highlightRepository: IHighlightRepository): ViewModel() {
    private val _SearchItemList: MutableStateFlow<List<SearchItem>> = MutableStateFlow(emptyList())
    val searchItemList = _SearchItemList.asStateFlow()

    init {
        getAllHighlight()
    }

    private fun getAllHighlight(){
        viewModelScope.launch {
            highlightRepository.getAll().collect { highlightList ->

                val searchItemList = arrayListOf<SearchItem>()

                highlightList.groupBy { it.type }.forEach { key, value ->

                    val searchItem = SearchItem(
                        id = UUID.randomUUID().toString(),
                        title = key.value,
                        items = value
                    )

                    searchItemList.add(searchItem)

                }

                _SearchItemList.value = searchItemList

            }
        }
    }

}