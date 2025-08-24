package com.sm.keepmarket.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.IHighlightRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.domain.model.FeaturedCard
import com.sm.keepmarket.domain.model.Highlight
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.util.FeaturedType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class HomeViewModel(
    private val marketRepository: IMarketRepository,
    private val pantryRepository: IPantryRepository,
    private val highlightRepository: IHighlightRepository
) : ViewModel() {


    private val _FeaturedCardList: MutableStateFlow<List<FeaturedCard>> = MutableStateFlow(emptyList())
    private val _HighlightList: MutableStateFlow<List<Highlight>> = MutableStateFlow(emptyList())
    var featuredCardList = _FeaturedCardList.asStateFlow()
    var highlightList = _HighlightList.asStateFlow()


    init {
        getAllFeaturedCardList()
        getAllHighlight()
    }

    private fun getAllFeaturedCardList() {
        viewModelScope.launch {

            combine(
                marketRepository.getAll(),
                pantryRepository.getAll()
            ) { marketList, pantryList ->
                val marketCards = marketList.map {
                    FeaturedCard(
                        name = it.name,
                        featuredType = FeaturedType.MARKET,
                        lastUpdate = it.lastUpdate
                    )
                }

                val pantryCards = pantryList.map {
                    FeaturedCard(
                        name = it.name,
                        featuredType = FeaturedType.PANTRY,
                        lastUpdate = it.lastUpdate
                    )
                }

                marketCards + pantryCards
            }.collect { featuredCardList ->

                _FeaturedCardList.value = featuredCardList.sortedByDescending {
                    it.lastUpdate
                }

            }
        }
    }

    private fun getAllHighlight(){
        viewModelScope.launch {
            highlightRepository.getAll().collect { highlightList ->
                _HighlightList.value = highlightList
            }
        }
    }

    fun createMarketList(name: String){

        val newMarketList = Market(
            id = UUID.randomUUID().toString(),
            name = name,
            createdDate = LocalDateTime.now(),
            lastUpdate = LocalDateTime.now(),
            items = emptyList()
        )

        viewModelScope.launch {
            marketRepository.insert(newMarketList)
        }

    }

    fun createPantryList(name: String){

        val newPantryList = Pantry(
            id = UUID.randomUUID().toString(),
            name = name,
            createdDate = LocalDateTime.now(),
            lastUpdate = LocalDateTime.now(),
            items = emptyList()
        )

        viewModelScope.launch {
            pantryRepository.insert(newPantryList)
        }
    }


}