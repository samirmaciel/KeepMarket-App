package com.sm.keepmarket.data.datasource.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.sm.keepmarket.data.datasource.datasourceInterface.IHighlightDatasource
import com.sm.keepmarket.data.model.HighlightEntity
import com.sm.keepmarket.domain.FireStoreCollections.HIGHLIGHT
import com.sm.keepmarket.domain.FireStoreCollections.USERS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.tasks.await

class HighlightFirebaseDatasourceImpl(private val firestore: FirebaseFirestore): IHighlightDatasource {

    private val TAG = this.javaClass.name

    override suspend fun getAllByUserId(userID: String): Flow<List<HighlightEntity>> =
        flow {
            val highlightList: List<HighlightEntity> = firestore
                .collection(USERS)
                .document(userID)
                .collection(HIGHLIGHT)
                .orderBy("createdDate", Query.Direction.DESCENDING)
                .limit(10)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(HighlightEntity::class.java) }

            emit(highlightList)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de highlight", e)
            emit(emptyList())
        }

    override suspend fun getById(userID: String, highlightID: String): Flow<HighlightEntity?> =
        flow {
            val highlightEntity = firestore
                .collection(USERS)
                .document(userID)
                .collection(HIGHLIGHT)
                .document(highlightID)
                .get()
                .await().toObject(HighlightEntity::class.java)

            emit(highlightEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de highlight", e)
            emit(null)
        }

    override suspend fun deleteAllByUserID(userID: String) {

        val all = getAllByUserId(userID).firstOrNull()
        all?.forEach { highlightEntity ->

            highlightEntity.id?.let {
                firestore
                    .collection(USERS)
                    .document(userID)
                    .collection(HIGHLIGHT)
                    .document(it)
                    .delete()
            }
        }
    }

    override suspend fun deleteByID(userID: String, highlightID: String) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(HIGHLIGHT)
            .document(highlightID)
            .delete()
    }

    override suspend fun insert(userID: String, highlightEntity: HighlightEntity) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(HIGHLIGHT)
            .document(highlightEntity.id ?: throw IllegalArgumentException("Highlight ID should be not null"))
            .set(highlightEntity)
    }
}