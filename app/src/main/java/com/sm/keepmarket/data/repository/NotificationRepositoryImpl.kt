package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.INotificationDatasource
import com.sm.keepmarket.data.mapper.NotificationMapper
import com.sm.keepmarket.data.repository.repositoryInterface.INotificationRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IUserRepository
import com.sm.keepmarket.domain.model.NotificationItem
import com.sm.keepmarket.util.Mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NotificationRepositoryImpl(private val notificationDatasource: INotificationDatasource, private val userRepository: IUserRepository) :
    INotificationRepository {
    override suspend fun getAll(): Flow<List<NotificationItem>> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

           notificationDatasource.getAllByUserID(userID).collect { itemList ->
               emit(itemList.map { NotificationMapper.toNotification(it) })
           }
       }.catch { e -> emit(emptyList())}
    }

    override suspend fun deleteAll() {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        notificationDatasource.deleteAllByUserID(userID)
    }

    override suspend fun insert(notificationItemEntity: NotificationItem) {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        notificationDatasource.insert(NotificationMapper.toEntity(notificationItemEntity), userID)
    }

    override suspend fun delete(notificationItemEntity: NotificationItem) {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        notificationDatasource.deleteByID(notificationItemEntity.id, userID)
    }
}