package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.domain.model.NotificationItem
import kotlinx.coroutines.flow.Flow

interface INotificationRepository {

    suspend fun getAll(): Flow<List<NotificationItem>>
    suspend fun deleteAll()
    suspend fun insert(notificationItemEntity: NotificationItem)
    suspend fun delete(notificationItemEntity: NotificationItem)
}