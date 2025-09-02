package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.INotificationDatasource
import com.sm.keepmarket.data.mapper.NotificationMapper
import com.sm.keepmarket.data.repository.repositoryInterface.INotificationRepository
import com.sm.keepmarket.domain.model.NotificationItem
import com.sm.keepmarket.util.Mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NotificationRepositoryImpl(private val notificationDatasource: INotificationDatasource) :
    INotificationRepository {
    override suspend fun getAll(): Flow<List<NotificationItem>> {
       return flow {
           notificationDatasource.getAll().collect { itemList ->
               emit(itemList.map { NotificationMapper.toNotification(it) })
           }
       }
    }

    override suspend fun deleteAll() {
        notificationDatasource.deleteAll()
    }

    override suspend fun insert(notificationItemEntity: NotificationItem) {
        notificationDatasource.insert(NotificationMapper.toEntity(notificationItemEntity))
    }

    override suspend fun delete(notificationItemEntity: NotificationItem) {
        notificationDatasource.delete(NotificationMapper.toEntity(notificationItemEntity))
    }
}