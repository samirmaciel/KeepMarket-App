package com.sm.keepmarket.presentation.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.INotificationRepository
import com.sm.keepmarket.domain.model.NotificationItem
import com.sm.keepmarket.util.Mock
import com.sm.keepmarket.util.UiState
import com.sm.keepmarket.util.UiStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotificationsViewModel(private val notificationsRepository: INotificationRepository) : ViewModel() {

    private val _UiState: MutableStateFlow<UiStateView<List<NotificationItem>>> = MutableStateFlow(
        UiStateView.Loading)
    val uiState = _UiState.asStateFlow()

    init {
        getAllNotifications()

        Mock.getNotificationList().forEach {
            viewModelScope.launch {
                notificationsRepository.insert(it)
                getAllNotifications()
            }
        }
    }

    private fun getAllNotifications(){
        viewModelScope.launch {
            notificationsRepository.getAll().collect { itemList ->
                _UiState.update {
                    UiStateView.Success(itemList)
                }
            }
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            _UiState.update {
                UiStateView.Loading
            }
            notificationsRepository.deleteAll()

            _UiState.update {
                UiStateView.Success(emptyList())
            }
        }
    }

    fun delete(notificationItem: NotificationItem){
        viewModelScope.launch {

            val oldList = (_UiState.value as UiStateView.Success).data

            _UiState.update {
                UiStateView.Loading
            }

            notificationsRepository.delete(notificationItem)

            _UiState.update {
                UiStateView.Success(oldList - notificationItem)
            }
        }
    }


}