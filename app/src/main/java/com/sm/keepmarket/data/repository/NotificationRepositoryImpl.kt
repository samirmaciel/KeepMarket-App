package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.INotificationDatasource
import com.sm.keepmarket.data.repository.repositoryInterface.INotificationRepository
import com.sm.keepmarket.domain.model.Notification
import com.sm.keepmarket.util.Mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotificationRepositoryImpl(notificationDatasource: INotificationDatasource) :
    INotificationRepository {
    override suspend fun getAll(): Flow<List<Notification>> {
       return flow {
           emit(Mock.getNotificationList())
       }
    }

    override suspend fun insert(notificationEntity: Notification) {

    }

    override suspend fun delete(notificationEntity: Notification) {

    }
}