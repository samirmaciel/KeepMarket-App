package com.sm.keepmarket.presentation.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.INotificationRepository
import com.sm.keepmarket.domain.model.Notification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotificationsViewModel(private val notificationsRepository: INotificationRepository) : ViewModel() {

    private val _NotificationList: MutableStateFlow<List<Notification>> = MutableStateFlow(emptyList())
    val notificationsList = _NotificationList.asStateFlow()

    init {
        getAllNotifications()
    }

    private fun getAllNotifications(){
        viewModelScope.launch {
            notificationsRepository.getAll().collect {
                _NotificationList.value = it
            }
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            _NotificationList.value.forEach {
                notificationsRepository.delete(it)
            }
        }
    }

}