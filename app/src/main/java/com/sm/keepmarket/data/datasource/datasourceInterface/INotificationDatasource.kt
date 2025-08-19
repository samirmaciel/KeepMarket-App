package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.NotificationEntity
import kotlinx.coroutines.flow.Flow

interface INotificationDatasource {

    suspend fun getAll(): Flow<List<NotificationEntity>>
    suspend fun insert(notificationEntity: NotificationEntity)
    suspend fun delete(notificationEntity: NotificationEntity)
}