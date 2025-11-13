package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.NotificationEntity
import kotlinx.coroutines.flow.Flow

interface INotificationDatasource {

    suspend fun getAllByUserID(userID: String): Flow<List<NotificationEntity>>
    suspend fun deleteAllByUserID(userID: String)
    suspend fun insert(notificationEntity: NotificationEntity, userID: String)
    suspend fun deleteByID(notificationID: String, userID: String)
}