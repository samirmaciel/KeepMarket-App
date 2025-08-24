package com.sm.keepmarket.presentation.marketList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.domain.model.MarketItem
import com.sm.keepmarket.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

class MarketListViewModel(private val marketRepository: IMarketRepository, private val marketItemRepository: IMarketItemRepository): ViewModel() {

    private val _UiState : MutableStateFlow<MarketListUiState> = MutableStateFlow(MarketListUiState())
    val uiState = _UiState.asStateFlow()

    init {
        _UiState.update { currentState ->
            currentState.copy(
                state = UiState.LOADING
            )
        }
    }

    fun getMarket(id: String){
        viewModelScope.launch {
            marketRepository.getById(id).collect { market ->
                _UiState.update { uiState ->
                    uiState.copy(
                        market = market,
                        state = UiState.LOADED
                    )
                }
            }
        }
    }

    fun updateItem(marketItem: MarketItem){
        viewModelScope.launch {
            marketItemRepository.insert(marketItem)
        }
    }

    fun addNewItem(name: String){

        val market = _UiState.value.market

        if(market?.id == null) return

        val newMarketItem = MarketItem(
            id = UUID.randomUUID().toString(),
            marketId = market.id,
            name = name,
            createdDate = LocalDateTime.now()
        )

        viewModelScope.launch {

            _UiState.update { currentState ->
                currentState.copy(
                    market = currentState.market?.copy(
                        items = currentState.market.items.plus(newMarketItem)
                    )
                )
            }

            marketItemRepository.insert(newMarketItem)
        }
    }

    fun deleteMarketItem(marketItem: MarketItem){
        viewModelScope.launch {
            marketItemRepository.delete(marketItem)
        }
    }

    fun getTotalItemCheckedValue(): BigDecimal {

        val items = _UiState.value.market?.items?.filter { it.isChecked }

        if(items == null) return BigDecimal.ZERO

        return items.sumOf { it.getTotalPrice() }
    }

    fun deleteItem(marketItem: MarketItem){

        _UiState.update { currentState ->
            currentState.copy(
                lastDeleteItem = marketItem
            )
        }

        viewModelScope.launch {

            _UiState.update { currentState ->
                currentState.copy(
                    market = _UiState.value.market?.copy(
                        items = _UiState.value.market?.items?.filter { it.id != marketItem.id } ?: emptyList()
                    )
                )
            }

            marketItemRepository.delete(marketItem)
        }

    }

    fun editItem(marketItem: MarketItem){
        _UiState.update { currentState ->
            currentState.copy(
                market = _UiState.value.market?.copy(
                    items = _UiState.value.market?.items?.map { item -> if(item.id == marketItem.id) marketItem else item } ?: emptyList()
                )
            )

        }

    }

}