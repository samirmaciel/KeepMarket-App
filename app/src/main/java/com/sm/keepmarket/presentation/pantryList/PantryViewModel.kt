package com.sm.keepmarket.presentation.pantryList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.domain.model.MarketItem
import com.sm.keepmarket.domain.model.PantryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

class PantryViewModel(private val pantryRepository: IPantryRepository, private val pantryItemRepository: IPantryItemRepository) : ViewModel() {

    private val _UiState : MutableStateFlow<PantryListUiState> = MutableStateFlow(PantryListUiState())
    val uiState = _UiState.asStateFlow()

    fun getPantry(pantryId: String){
        viewModelScope.launch {
            pantryRepository.getById(pantryId).collect { pantry ->
                _UiState.update { currentState ->
                    currentState.copy(
                        pantry = pantry
                    )
                }
            }
        }
    }

    fun removeExpiredItems(){
        val oldPantryItemList = _UiState.value.pantry?.items

        _UiState.update { currentState ->
            currentState.copy(
                pantry = currentState.pantry?.copy(
                    items = oldPantryItemList?.filter { item ->
                        !item.dueDate.isEqual(LocalDate.now()) && !item.dueDate.isBefore(LocalDate.now())
                    } ?: emptyList()
                )
            )
        }
    }

    fun addNewItem(itemName: String, itemAmount : Int, itemDueDate: LocalDate){

        val pantryId = _UiState.value.pantry?.id

        if(pantryId == null){
            return
        }

        val newPantryItem = PantryItem(
            id = UUID.randomUUID().toString(),
            pantryId = pantryId,
            name = itemName,
            dueDate = itemDueDate,
            amount = itemAmount
        )

        _UiState.update { currentState ->
            currentState.copy(
                pantry = currentState.pantry?.copy(
                    items = currentState.pantry.items.plus(newPantryItem)
                )
            )
        }

        viewModelScope.launch {
            pantryItemRepository.insert(newPantryItem)
        }
    }

    fun deleteItem(pantryItem: PantryItem) {

        _UiState.update { currentState ->
            currentState.copy(
                lastDeleteItem = pantryItem
            )
        }

        viewModelScope.launch {

            _UiState.update { currentState ->
                currentState.copy(
                    pantry = _UiState.value.pantry?.copy(
                        items = _UiState.value.pantry?.items?.filter { it.id != pantryItem.id }
                            ?: emptyList()
                    )
                )
            }

            pantryItemRepository.delete(pantryItem)
        }

    }

    fun editItem(pantryItem: PantryItem) {
        _UiState.update { currentState ->
            currentState.copy(
                pantry = _UiState.value.pantry?.copy(
                    items = _UiState.value.pantry?.items?.map { item -> if (item.id == pantryItem.id) pantryItem else item }
                        ?: emptyList()
                )
            )
        }

    }

}