package com.sm.keepmarket.presentation.marketList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.R
import com.sm.keepmarket.data.repository.HighlightRepositoryImpl
import com.sm.keepmarket.data.repository.repositoryInterface.IHighlightRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemStateRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.domain.model.Highlight
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.MarketItem
import com.sm.keepmarket.domain.model.MarketItemState
import com.sm.keepmarket.domain.model.toItemState
import com.sm.keepmarket.util.CurrencyUtil
import com.sm.keepmarket.util.HighlightType
import com.sm.keepmarket.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.Locale
import java.util.UUID
import kotlin.math.abs

class MarketListViewModel(
    private val marketRepository: IMarketRepository,
    private val marketItemRepository: IMarketItemRepository,
    private val marketItemStateRepository: IMarketItemStateRepository,
    private val highlightRepository: IHighlightRepository
) : ViewModel() {

    private val _UiState: MutableStateFlow<MarketListUiState> =
        MutableStateFlow(MarketListUiState())
    private var marketItemStateList: List<MarketItemState> = emptyList()
    val uiState = _UiState.asStateFlow()


    suspend fun checkItemsState(market: Market): Market {

        val updateMarketItemList = mutableListOf<MarketItem>()

        marketItemStateRepository.getAllByMarketId(market.id).collect { itemStateList ->

            marketItemStateList = itemStateList

            for (marketItem in market.items) {

                val itemState = itemStateList.filter { it.marketItemId == marketItem.id }.maxByOrNull { it.createdDate }

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
            productName = name,
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

        updateItem(marketItem)
        saveItemState(marketItem.toItemState())
    }

    fun saveItemState(item: MarketItemState) {

        if(marketItemStateList.isEmpty() || !marketItemStateList.contains(item)){
            marketItemStateList = marketItemStateList.plus(item)
        }else{
            marketItemStateList = marketItemStateList.map {
                if(it.id == item.id){
                    item
                }else{
                    it
                }
            }
        }

        viewModelScope.launch {
            marketItemStateRepository.insert(item)
        }
    }

    fun finishItemState(){
        val updatedItemStateList = marketItemStateList.map {
            if(it.isChecked){
                it.copy(enabled = false)
            } else {
                it
            }
        }

        viewModelScope.launch {
            updatedItemStateList.forEach { newItemState ->

                val lastItemState = marketItemStateRepository.getLastByProductName(newItemState.productName).first()

                var itemStateType: HighlightType? = null

                lastItemState?.let {

                    val lastPrice = lastItemState.price
                    val newPrice = newItemState.price

                    if(lastPrice < newPrice){
                        itemStateType = HighlightType.PRICE_INCREASE
                    } else if (lastPrice > newPrice){
                        itemStateType = HighlightType.PRICE_DECREASE
                    }
                }


                if(itemStateType != null){

                    val icon = getIcon(itemStateType)
                    val subTitle = getSubTitle(itemStateType)

                    val newHighlight = Highlight(
                        id = UUID.randomUUID().toString(),
                        title = "${newItemState.name} (${newItemState.productName})",
                        subTitle = subTitle,
                        icon = icon,
                        type = itemStateType,
                        description = "${CurrencyUtil.bigDecimalToCurrency(newItemState.price, Locale("pt", "BR"))} (${CurrencyUtil.bigDecimalToCurrency( newItemState.price
                            .subtract(lastItemState?.price ?: BigDecimal.ZERO)
                            .abs(), Locale("pt", "BR"))})"
                    )

                    CurrencyUtil.bigDecimalToCurrency( newItemState.price
                        .subtract(lastItemState?.price ?: BigDecimal.ZERO)
                        .abs(), Locale("pt", "BR"))

                    highlightRepository.insert(newHighlight)
                }

                marketItemStateRepository.insert(newItemState)

            }

            getMarket(_UiState.value.market?.id ?: "")
        }
    }

    private fun getSubTitle(highlightType: HighlightType): String {
        return when(highlightType){
            HighlightType.PRICE_DECREASE -> "Baixou o preço"
            HighlightType.PRICE_INCREASE -> "Aumentou o preço"
            else -> {
                ""
            }
        }
    }

    private fun getIcon(highlightType: HighlightType): Int {
        return when(highlightType){
            HighlightType.PRICE_DECREASE -> R.drawable.arrowdowngreenicon
            HighlightType.PRICE_INCREASE -> R.drawable.arrowiconupicon
            else -> {
                R.drawable.homeicon
            }
        }
    }

}