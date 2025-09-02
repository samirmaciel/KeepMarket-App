package com.sm.keepmarket.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.util.UiStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class SplashViewModel(
    private val marketRepository: IMarketRepository,
    private val pantryRepository: IPantryRepository
) : ViewModel() {

    private val _userName: MutableStateFlow<String> = MutableStateFlow("User")
    private val _ListState: MutableStateFlow<UiStateView<Boolean>> =
        MutableStateFlow(UiStateView.Loading)
    val userName = _userName.asStateFlow()
    val listState = _ListState.asStateFlow()

    init {
        getUserName()
    }

    fun hasCreatedList() {

        viewModelScope.launch {

            combine(
                marketRepository.getAll(),
                pantryRepository.getAll()
            ) { marketList, pantryList ->
                marketList.isNotEmpty() || pantryList.isNotEmpty()
            }.collect { hasList ->
                _ListState.update {
                    UiStateView.Success(hasList)
                }
            }
        }
    }

    private fun getUserName() {

        viewModelScope.launch {
            _userName.update {
                "User"
            }
        }
    }

    fun createMarketList(name: String) {

        _ListState.update {
            UiStateView.Loading
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
            _ListState.update {
                UiStateView.Success(true)
            }
        }
    }

    fun createPantryList(name: String) {

        _ListState.update {
            UiStateView.Loading
        }

        val newPantryList = Pantry(
            id = UUID.randomUUID().toString(),
            name = name,
            createdDate = LocalDateTime.now(),
            lastUpdate = LocalDateTime.now(),
            items = emptyList()
        )

        viewModelScope.launch {
            pantryRepository.insert(newPantryList)
            _ListState.update {
                UiStateView.Success(true)
            }
        }
    }
}