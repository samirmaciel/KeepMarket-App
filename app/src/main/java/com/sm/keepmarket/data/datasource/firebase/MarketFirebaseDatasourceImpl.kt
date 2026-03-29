package com.sm.keepmarket.data.datasource.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketDatasource
import com.sm.keepmarket.data.model.MarketEntity
import com.sm.keepmarket.domain.model.FireStoreCollections.MARKET
import com.sm.keepmarket.domain.model.FireStoreCollections.USERS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class MarketFirebaseDatasourceImpl(private val firestore: FirebaseFirestore): IMarketDatasource {

    private val TAG = this.javaClass.name

    override suspend fun getAllByUserID(userID: String): Flow<List<MarketEntity>> =
        flow {
            val marketEntityList: List<MarketEntity> = firestore
                .collection(USERS)
                .document(userID)
                .collection(MARKET)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(MarketEntity::class.java) }
            emit(marketEntityList)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de market", e)
            emit(emptyList())
        }

    override suspend fun getById(
        userID: String,
        marketID: String
    ): Flow<MarketEntity?> =
        flow {
            val marketEntity = firestore
                .collection(USERS)
                .document(userID)
                .collection(MARKET)
                .document(marketID)
                .get()
                .await().toObject(MarketEntity::class.java)

            emit(marketEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar marketEntity", e)
            emit(null)
        }

    override suspend fun deleteByID(userID: String, marketID: String) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(MARKET)
            .document(marketID)
            .delete()
    }

    override suspend fun deleteAllByUserID(userID: String) {
        val all = getAllByUserID(userID).firstOrNull()
        all?.forEach { marketEntity ->

            marketEntity.id?.let {
                firestore
                    .collection(USERS)
                    .document(userID)
                    .collection(MARKET)
                    .document(it)
                    .delete()
            }
        }
    }

    override suspend fun insert(
        userID: String,
        marketEntity: MarketEntity
    ) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(MARKET)
            .document(marketEntity.id ?: throw IllegalArgumentException("Market ID should be not null"))
            .set(marketEntity)
    }
}