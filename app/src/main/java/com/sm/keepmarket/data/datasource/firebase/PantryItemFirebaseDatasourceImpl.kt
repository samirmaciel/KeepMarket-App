package com.sm.keepmarket.data.datasource.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryItemDatasource
import com.sm.keepmarket.data.model.PantryItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class PantryItemFirebaseDatasourceImpl(private val firestore: FirebaseFirestore): IPantryItemDatasource {

    private val TAG = this.javaClass.name

    override suspend fun getAllByPantryID(
        pantryID: String,
        userID: String
    ): Flow<List<PantryItemEntity>>
        =
        flow {
            val pantryItemEntity: List<PantryItemEntity> = firestore
                .collection("USERS")
                .document(userID)
                .collection("PANTRY_ITEM")
                .whereEqualTo("pantryId", pantryID)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(PantryItemEntity::class.java) }

            emit(pantryItemEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de pantryItem", e)
            emit(emptyList())
        }


    override suspend fun getById(
        pantryItemID: String,
        userID: String
    ): Flow<PantryItemEntity?> =
        flow {
            val pantryItemEntity = firestore
                .collection("USERS")
                .document(userID)
                .collection("PANTRY_ITEM")
                .document(pantryItemID)
                .get()
                .await().toObject(PantryItemEntity::class.java)

            emit(pantryItemEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de pantryItem", e)
            emit(null)
        }

    override suspend fun deleteByID(pantryItemID: String, userID: String) {
        firestore
            .collection("USERS")
            .document(userID)
            .collection("PANTRY_ITEM")
            .document(pantryItemID)
            .delete()
    }

    override suspend fun deleteAllByPantryID(pantryID: String, userID: String) {
        val all = getAllByPantryID(pantryID, userID).firstOrNull()
        all?.forEach { pantryItemEntity ->

            pantryItemEntity.id?.let {
                firestore
                    .collection("USERS")
                    .document(userID)
                    .collection("PANTRY_ITEM")
                    .document(it)
                    .delete()
            }
        }
    }

    override suspend fun insert(
        pantryItemEntity: PantryItemEntity,
        userID: String
    ) {
        firestore
            .collection("USERS")
            .document(userID)
            .collection("PANTRY_ITEM")
            .document(pantryItemEntity.id ?: throw IllegalArgumentException("PantryItem ID should be not null"))
            .set(pantryItemEntity)
    }
}