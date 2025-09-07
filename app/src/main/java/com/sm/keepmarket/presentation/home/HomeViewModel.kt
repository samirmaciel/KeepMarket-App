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
import com.sm.keepmarket.util.UiStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class HomeViewModel(
    private val marketRepository: IMarketRepository,
    private val pantryRepository: IPantryRepository,
    private val highlightRepository: IHighlightRepository
) : ViewModel() {

    private val _FeaturedCardListState: MutableStateFlow<UiStateView<List<FeaturedCard>>> = MutableStateFlow(
        UiStateView.Loading
    )
    private val _HighlightListState: MutableStateFlow<UiStateView<List<Highlight>>> = MutableStateFlow(
        UiStateView.Loading
    )
    val featuredCardListState = _FeaturedCardListState.asStateFlow()
    val highlightListState = _HighlightListState.asStateFlow()


    fun getAllFeaturedCardList() {

        _FeaturedCardListState.update {
            UiStateView.Loading
        }

        viewModelScope.launch {

            combine(
                marketRepository.getAll(),
                pantryRepository.getAll()
            ) { marketList, pantryList ->
                val marketCards = marketList.map {
                    FeaturedCard(
                        id = it.id,
                        name = it.name,
                        featuredType = FeaturedType.MARKET,
                        lastUpdate = it.lastUpdate
                    )
                }

                val pantryCards = pantryList.map {
                    FeaturedCard(
                        id = it.id,
                        name = it.name,
                        featuredType = FeaturedType.PANTRY,
                        lastUpdate = it.lastUpdate
                    )
                }

                marketCards + pantryCards
            }.collect { featuredCardList ->

                _FeaturedCardListState.update {
                    UiStateView.Success(featuredCardList.sortedByDescending { it.lastUpdate })
                }

            }
        }
    }

    fun getAllHighlight() {

        _HighlightListState.value = UiStateView.Loading

        viewModelScope.launch {

            highlightRepository.getAll().collect { highlightList ->
                _HighlightListState.update {
                    UiStateView.Success(highlightList)
                }
            }
        }
    }

    fun createMarketList(name: String) {

        _FeaturedCardListState.value = UiStateView.Loading

        val newMarketList = Market(
            id = UUID.randomUUID().toString(),
            name = name,
            createdDate = LocalDateTime.now(),
            lastUpdate = LocalDateTime.now(),
            items = emptyList()
        )

        viewModelScope.launch {
            marketRepository.insert(newMarketList)

            val featuredCardListState = _FeaturedCardListState.value

            if(featuredCardListState is UiStateView.Success){

                val oldList = featuredCardListState.data

                val newList = oldList + FeaturedCard(
                    id= newMarketList.id,
                    name = name,
                    featuredType = FeaturedType.MARKET,
                    lastUpdate = LocalDateTime.now()
                )
                _FeaturedCardListState.update {
                    UiStateView.Success(newList)
                }
            }
        }
    }

    fun createPantryList(name: String) {

        _FeaturedCardListState.value = UiStateView.Loading

        val newPantryList = Pantry(
            id = UUID.randomUUID().toString(),
            name = name,
            createdDate = LocalDateTime.now(),
            lastUpdate = LocalDateTime.now(),
            items = emptyList()
        )

        viewModelScope.launch {
            pantryRepository.insert(newPantryList)

            val featuredCardListState = _FeaturedCardListState.value

            if (featuredCardListState is UiStateView.Success) {

                val oldList = featuredCardListState.data

                val newList = oldList + FeaturedCard(
                    id = newPantryList.id,
                    name = name,
                    featuredType = FeaturedType.PANTRY,
                    lastUpdate = LocalDateTime.now()
                )

                _FeaturedCardListState.update {
                    UiStateView.Success(newList)
                }

            }
        }
    }

}