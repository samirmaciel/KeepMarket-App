package com.sm.keepmarket.presentation.pantryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.domain.model.PantryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID

class PantryViewModel(private val pantryRepository: IPantryRepository, private val pantryItemRepository: IPantryItemRepository) : ViewModel() {

    private val _Pantry : MutableStateFlow<Pantry?> = MutableStateFlow(null)
    private var lastItemRemoved: PantryItem? = null
    val pantry = _Pantry.asStateFlow()

    init {

        viewModelScope.launch {
            _Pantry.collect { pantry ->
                pantry?.let {
                    pantryRepository.insert(it)
                }
            }
        }

    }

    fun getPantry(pantryId: String){
        viewModelScope.launch {
            pantryRepository.getById(pantryId).collect { pantry ->
                _Pantry.value = pantry
            }
        }
    }

    fun removeExpiredItems(){
        val oldPantryItemList = _Pantry.value?.items

        _Pantry.update { pantry ->
            pantry?.copy(
                items = oldPantryItemList?.filter { item ->
                    !item.dueDate.isEqual(LocalDate.now()) && !item.dueDate.isBefore(LocalDate.now())
                } ?: emptyList()
            )
        }
    }

    fun addItem(itemName: String, itemAmount : Int, itemDueDate: LocalDate){

        val pantry = _Pantry.value

        if(pantry == null){
            return
        }

        val newPantryItem = PantryItem(
            id = UUID.randomUUID().toString(),
            pantryId = pantry.id,
            name = itemName,
            dueDate = itemDueDate,
            amount = itemAmount
        )

        viewModelScope.launch {
            pantryItemRepository.insert(newPantryItem)
        }
    }

    fun removeItem(pantryItem: PantryItem){
        viewModelScope.launch {
            lastItemRemoved = pantryItem
            pantryItemRepository.delete(pantryItem)
        }
    }

}