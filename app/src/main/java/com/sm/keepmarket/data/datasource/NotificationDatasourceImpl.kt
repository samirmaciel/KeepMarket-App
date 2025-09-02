package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.INotificationDatasource
import com.sm.keepmarket.data.db.dao.NotificationDao
import com.sm.keepmarket.data.mapper.NotificationMapper
import com.sm.keepmarket.data.model.NotificationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotificationDatasourceImpl(private val dao: NotificationDao) : INotificationDatasource {
    override suspend fun getAll(): Flow<List<NotificationEntity>> {
        return flow {
            emit(dao.getAll())
        }
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun insert(notificationEntity: NotificationEntity) {
        dao.insert(notificationEntity)
    }

    override suspend fun delete(notificationEntity: NotificationEntity) {
        dao.delete(notificationEntity)
    }
}