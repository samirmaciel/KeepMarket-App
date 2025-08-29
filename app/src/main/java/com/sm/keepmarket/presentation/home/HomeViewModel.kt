package com.sm.keepmarket.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.IHighlightRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.domain.model.FeaturedCard
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.util.FeaturedType
import com.sm.keepmarket.util.UiState
import kotlinx.coroutines.delay
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


    private val _UiState: MutableStateFlow<HomeViewUiState> = MutableStateFlow(HomeViewUiState())
    var uiState = _UiState.asStateFlow()


    init {
        getAllFeaturedCardList()
        getAllHighlight()
    }

    private fun getAllFeaturedCardList() {

        _UiState.update { currentState ->
            currentState.copy(state = UiState.LOADING)
        }

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

                _UiState.update { currentState ->
                    currentState.copy(
                        featuredCardList = featuredCardList,
                        state = UiState.LOADED
                    )
                }

            }
        }
    }

    private fun getAllHighlight() {

        _UiState.update { currentState ->
            currentState.copy(highlightListState = UiState.LOADING)
        }

        viewModelScope.launch {

            delay(3000)

            highlightRepository.getAll().collect { highlightList ->
                _UiState.update { currentState ->
                    currentState.copy(
                        highlightList = highlightList,
                        highlightListState = UiState.LOADED
                    )
                }
            }
        }
    }

    fun createMarketList(name: String) {

        _UiState.update { currentState ->
            currentState.copy(featuredListState = UiState.LOADING)
        }

        val newMarketList = Market(
            id = UUID.randomUUID().toString(),
            name = name,
            createdDate = LocalDateTime.now(),
            lastUpdate = LocalDateTime.now(),
            items = emptyList()
        )

        viewModelScope.launch {
            marketRepository.insert(newMarketList)

            _UiState.update { currentState ->

                val newFeaturedCardList = currentState.featuredCardList + FeaturedCard(
                    name = name,
                    featuredType = FeaturedType.MARKET,
                    lastUpdate = LocalDateTime.now()
                )

                currentState.copy(
                    featuredCardList = newFeaturedCardList,
                    featuredListState = UiState.LOADED
                )
            }
        }
    }

    fun createPantryList(name: String) {

        val newPantryList = Pantry(
            id = UUID.randomUUID().toString(),
            name = name,
            createdDate = LocalDateTime.now(),
            lastUpdate = LocalDateTime.now(),
            items = emptyList()
        )

        viewModelScope.launch {
            pantryRepository.insert(newPantryList)

            _UiState.update { currentState ->

                val newFeaturedCardList = currentState.featuredCardList + FeaturedCard(
                    name = name,
                    featuredType = FeaturedType.MARKET,
                    lastUpdate = LocalDateTime.now()
                )

                currentState.copy(
                    featuredCardList = newFeaturedCardList,
                    state = UiState.LOADED
                )
            }
        }


    }


}