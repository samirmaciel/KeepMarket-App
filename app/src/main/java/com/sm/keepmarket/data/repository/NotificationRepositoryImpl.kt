package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.INotificationDatasource
import com.sm.keepmarket.data.repository.repositoryInterface.INotificationRepository
import com.sm.keepmarket.domain.model.Notification
import kotlinx.coroutines.flow.Flow

class NotificationRepositoryImpl(notificationDatasource: INotificationDatasource) :
    INotificationRepository {
    override suspend fun getAll(): Flow<List<Notification>> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(notificationEntity: Notification) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(notificationEntity: Notification) {
        TODO("Not yet implemented")
    }
}