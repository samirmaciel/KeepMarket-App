package com.sm.keepmarket.data.datasource.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryDatasource
import com.sm.keepmarket.data.model.MarketEntity
import com.sm.keepmarket.data.model.PantryEntity
import com.sm.keepmarket.domain.model.FireStoreCollections.PANTRY
import com.sm.keepmarket.domain.model.FireStoreCollections.USERS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class PantryFirebaseDatasourceImpl(private val firestore: FirebaseFirestore): IPantryDatasource {

    private val TAG = this.javaClass.name

    override suspend fun getAllByUserID(userID: String): Flow<List<PantryEntity>> =
        flow {
            val pantryEntityList: List<PantryEntity> = firestore
                .collection(USERS)
                .document(userID)
                .collection(PANTRY)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(PantryEntity::class.java) }
            emit(pantryEntityList)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de dispensas", e)
            emit(emptyList())
        }

    override suspend fun getById(
        userID: String,
        pantryID: String
    ): Flow<PantryEntity?> =
        flow {
            val marketEntity = firestore
                .collection(USERS)
                .document(userID)
                .collection(PANTRY)
                .document(pantryID)
                .get()
                .await().toObject(PantryEntity::class.java)

            emit(marketEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar pantryEntity", e)
            emit(null)
        }


    override suspend fun deleteByID(userID: String, pantryID: String) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(PANTRY)
            .document(pantryID)
            .delete()
    }

    override suspend fun deleteAllByUserID(userID: String) {
        val all = getAllByUserID(userID).firstOrNull()
        all?.forEach { pantryEntity ->
            pantryEntity.id?.let {
                firestore
                    .collection(USERS)
                    .document(userID)
                    .collection(PANTRY)
                    .document(it)
                    .delete()
            }
        }
    }

    override suspend fun insert(
        userID: String,
        pantryEntity: PantryEntity
    ) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(PANTRY)
            .document(pantryEntity.id ?: throw IllegalArgumentException("Pantry ID should be not null"))
            .set(pantryEntity)
    }


}