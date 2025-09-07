package com.sm.keepmarket.presentation.pantryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.util.UiStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class PantryListSelectionViewModel(private val pantryRepository: IPantryRepository): ViewModel() {

    private val _UiState: MutableStateFlow<UiStateView<List<Pantry>>> = MutableStateFlow(UiStateView.Loading)
    val uiState = _UiState.asStateFlow()

    fun getPantryList(){
        viewModelScope.launch {
            pantryRepository.getAll().collect{ pantryList ->
                _UiState.value = UiStateView.Success(pantryList)
            }
        }
    }

    fun updatePantry(pantry: Pantry){

        viewModelScope.launch {

            pantry.lastUpdate = LocalDateTime.now()

            pantryRepository.insert(pantry)

            if(_UiState.value is UiStateView.Success<*>){
                val oldList = _UiState.value as UiStateView.Success<List<Pantry>>

                val newList = oldList.data.map { if(it.id == pantry.id) pantry else it }

                _UiState.update {
                    UiStateView.Success(newList)
                }

            }
        }
    }

    fun createPantry(name: String){

        val newPantry = Pantry(
            id = UUID.randomUUID().toString(),
            name = name,
            createdDate = LocalDateTime.now(),
            lastUpdate = LocalDateTime.now(),
            items = emptyList()
        )

        viewModelScope.launch {

            pantryRepository.insert(newPantry)

            if(_UiState.value is UiStateView.Success<*>){
                val oldList = _UiState.value as UiStateView.Success<List<Pantry>>

                val newList = oldList.data.toMutableList()
                newList.add(newPantry)

                _UiState.update {
                    UiStateView.Success(newList)
                }
            }
        }
    }

    fun deletePantry(pantry: Pantry){
        viewModelScope.launch {

            pantryRepository.delete(pantry)

            if(_UiState.value is UiStateView.Success<*>){
                val oldList = _UiState.value as UiStateView.Success<List<Pantry>>

                val newList = oldList.data.toMutableList()
                newList.remove(pantry)

                _UiState.update {
                    UiStateView.Success(newList)
                }
            }
        }

    }
}

