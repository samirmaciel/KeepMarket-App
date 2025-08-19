package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.INotificationDatasource
import com.sm.keepmarket.data.db.dao.NotificationDao
import com.sm.keepmarket.data.model.NotificationEntity
import kotlinx.coroutines.flow.Flow

class NotificationDatasourceImpl(dao: NotificationDao) : INotificationDatasource {
    override suspend fun getAll(): Flow<List<NotificationEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(notificationEntity: NotificationEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(notificationEntity: NotificationEntity) {
        TODO("Not yet implemented")
    }
}