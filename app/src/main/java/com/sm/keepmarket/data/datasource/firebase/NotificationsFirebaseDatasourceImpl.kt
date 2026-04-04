package com.sm.keepmarket.data.datasource.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sm.keepmarket.data.datasource.datasourceInterface.INotificationDatasource
import com.sm.keepmarket.data.model.NotificationEntity
import com.sm.keepmarket.domain.FireStoreCollections.NOTIFICATION
import com.sm.keepmarket.domain.FireStoreCollections.USERS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class NotificationsFirebaseDatasourceImpl(private val firestore: FirebaseFirestore): INotificationDatasource{

    private val TAG = this.javaClass.name

    override suspend fun getAllByUserID(userID: String): Flow<List<NotificationEntity>> =
        flow {
            val notificationEntity: List<NotificationEntity> = firestore
                .collection(USERS)
                .document(userID)
                .collection(NOTIFICATION)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(NotificationEntity::class.java) }
            emit(notificationEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de notification", e)
            emit(emptyList())
        }

    override suspend fun deleteAllByUserID(userID: String) {
        val all = getAllByUserID(userID).firstOrNull()
        all?.forEach { notificationEntity ->

            notificationEntity.id?.let {
                firestore
                    .collection(USERS)
                    .document(userID)
                    .collection(NOTIFICATION)
                    .document(it)
                    .delete()
            }
        }
    }

    override suspend fun insert(
        notificationEntity: NotificationEntity,
        userID: String
    ) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(NOTIFICATION)
            .document(notificationEntity.id ?: throw IllegalArgumentException("Notification ID should be not null"))
            .set(notificationEntity)
    }

    override suspend fun deleteByID(notificationID: String, userID: String) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(NOTIFICATION)
            .document(notificationID)
            .delete()
    }
}