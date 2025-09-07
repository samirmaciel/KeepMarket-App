package com.sm.keepmarket.presentation.marketList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.util.UiStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class MarketListSelectionViewModel(private val marketRepository: IMarketRepository): ViewModel() {

    private val _UiState: MutableStateFlow<UiStateView<List<Market>>> = MutableStateFlow(UiStateView.Loading)
    val uiState = _UiState.asStateFlow()

    fun getMarketList(){
        viewModelScope.launch {
            marketRepository.getAll().collect{ marketList ->
                _UiState.update {
                    UiStateView.Success(marketList)
                }
            }
        }
    }

    fun updateMarket(market: Market){

        viewModelScope.launch {
            marketRepository.insert(market)

            if(_UiState.value is UiStateView.Success<*>){
                val oldList = _UiState.value as UiStateView.Success<List<Market>>

                val newList = oldList.data.map { if(it.id == market.id) market else it }

                _UiState.update {
                    UiStateView.Success(newList)
                }

            }
        }
    }

    fun createMarket(name: String){

        val newMarket = Market(
            id = UUID.randomUUID().toString(),
            name = name,
            createdDate = LocalDateTime.now(),
            lastUpdate = LocalDateTime.now(),
            items = emptyList()
        )

        viewModelScope.launch {

            marketRepository.insert(newMarket)

            if(_UiState.value is UiStateView.Success<*>){
                val oldList = _UiState.value as UiStateView.Success<List<Market>>

                val newList = oldList.data.toMutableList()
                newList.add(newMarket)

                _UiState.update {
                    UiStateView.Success(newList)
                }
            }
        }
    }

    fun deleteMarket(market: Market){
        viewModelScope.launch {

            marketRepository.delete(market)

            if(_UiState.value is UiStateView.Success<*>){
                val oldList = _UiState.value as UiStateView.Success<List<Market>>

                val newList = oldList.data.toMutableList()
                newList.remove(market)

                _UiState.update {
                    UiStateView.Success(newList)
                }
            }
        }

    }
}