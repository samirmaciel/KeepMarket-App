package com.sm.keepmarket.presentation.marketList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemStateRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.MarketItem
import com.sm.keepmarket.domain.model.MarketItemState
import com.sm.keepmarket.domain.model.toItemState
import com.sm.keepmarket.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

class MarketListViewModel(
    private val marketRepository: IMarketRepository,
    private val marketItemRepository: IMarketItemRepository,
    private val marketItemStateRepository: IMarketItemStateRepository
) : ViewModel() {

    private val _UiState: MutableStateFlow<MarketListUiState> =
        MutableStateFlow(MarketListUiState())
    val uiState = _UiState.asStateFlow()


    suspend fun checkItemsState(market: Market): Market {

        val updateMarketItemList = mutableListOf<MarketItem>()

        marketItemStateRepository.getAllByMarketId(market.id).collect { itemStateList ->
            for (marketItem in market.items) {

                var itemState: MarketItemState? = null

                itemStateList.forEach { marketItemState ->
                    if (marketItem.id == marketItemState.marketItemId) {
                        itemState = marketItemState
                        return@forEach
                    }
                }

                if (itemState != null) {
                    val updatedMarketItem = marketItem.copy(
                        price = itemState.price,
                        amount = itemState.amount,
                        isChecked = itemState.isChecked,
                        createdDate = itemState.createdDate
                    )
                    updateMarketItemList.add(updatedMarketItem)
                    continue
                }
                updateMarketItemList.add(marketItem)
            }
        }

        return market.copy(items = updateMarketItemList)

    }

    fun getMarket(id: String) {

        _UiState.update { uiState ->
            uiState.copy(
                state = UiState.LOADING
            )
        }

        viewModelScope.launch {
            marketRepository.getById(id).collect { market ->

                market?.let {
                    val updatedMarket = checkItemsState(market)
                    _UiState.update { currentState ->
                        currentState.copy(
                            market = updatedMarket,
                            state = UiState.LOADED
                        )
                    }
                }
            }
        }
    }

    fun updateItem(marketItem: MarketItem) {
        viewModelScope.launch {
            marketItemRepository.insert(marketItem)
        }
    }

    fun addNewItem(name: String) {

        val market = _UiState.value.market

        if (market?.id == null) return

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

    fun deleteMarketItem(marketItem: MarketItem) {
        viewModelScope.launch {
            marketItemRepository.delete(marketItem)
        }
    }

    fun getTotalItemCheckedValue(): BigDecimal {

        val items = _UiState.value.market?.items?.filter { it.isChecked }

        if (items == null) return BigDecimal.ZERO

        return items.sumOf { it.getTotalPrice() }
    }

    fun deleteItem(marketItem: MarketItem) {

        _UiState.update { currentState ->
            currentState.copy(
                lastDeleteItem = marketItem
            )
        }

        viewModelScope.launch {

            _UiState.update { currentState ->
                currentState.copy(
                    market = _UiState.value.market?.copy(
                        items = _UiState.value.market?.items?.filter { it.id != marketItem.id }
                            ?: emptyList()
                    )
                )
            }

            marketItemRepository.delete(marketItem)
        }

    }

    fun editItem(marketItem: MarketItem) {
        _UiState.update { currentState ->
            currentState.copy(
                market = _UiState.value.market?.copy(
                    items = _UiState.value.market?.items?.map { item -> if (item.id == marketItem.id) marketItem else item }
                        ?: emptyList()
                )
            )
        }
        saveItemState(marketItem)
    }

    fun saveItemState(item: MarketItem) {
        viewModelScope.launch {
            val marketItemState = item.toItemState()
            marketItemStateRepository.insert(marketItemState)
        }

    }

}