package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.domain.Notification
import kotlinx.coroutines.flow.Flow

interface INotificationRepository {

    suspend fun getAll(): Flow<List<Notification>>
    suspend fun insert(notificationEntity: Notification)
    suspend fun delete(notificationEntity: Notification)
}