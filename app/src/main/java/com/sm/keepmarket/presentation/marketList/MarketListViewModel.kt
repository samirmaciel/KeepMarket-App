package com.sm.keepmarket.presentation.marketList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.MarketItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

class MarketListViewModel(private val marketRepository: IMarketRepository, private val marketItemRepository: IMarketItemRepository): ViewModel() {

    private val _Market : MutableStateFlow<Market?> = MutableStateFlow(null)
    val market = _Market.asStateFlow()


    fun getMarket(id: String){
        viewModelScope.launch {
            marketRepository.getById(id).collect {
                _Market.value = it
            }
        }
    }

    fun updateItem(marketItem: MarketItem){
        viewModelScope.launch {
            marketItemRepository.insert(marketItem)
        }
    }

    fun addNewItem(name: String){

        val market = _Market.value

        if(market?.id == null) return

        val newMarketItem = MarketItem(
            id = UUID.randomUUID().toString(),
            marketId = market.id,
            name = name,
            createdDate = LocalDateTime.now()
        )

        viewModelScope.launch {
            marketItemRepository.insert(newMarketItem)
        }
    }

    fun deleteMarketItem(marketItem: MarketItem){
        viewModelScope.launch {
            marketItemRepository.delete(marketItem)
        }
    }

    fun getTotalItemCheckedValue(): BigDecimal {

        val items = _Market.value?.items?.filter { it.isChecked }

        if(items == null) return BigDecimal.ZERO

        return items.sumOf { it.getTotalPrice() }
    }

}