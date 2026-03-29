package com.sm.keepmarket.data.datasource.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemStateDatasource
import com.sm.keepmarket.data.model.MarketItemStateEntity
import com.sm.keepmarket.domain.model.FireStoreCollections.MARKET_ITEM_STATE
import com.sm.keepmarket.domain.model.FireStoreCollections.USERS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class MarketItemStateFirebaseDatasourceImpl(private val firestore: FirebaseFirestore): IMarketItemStateDatasource  {

    private val TAG = this.javaClass.name
    override suspend fun getAllByMarketID(
        marketID: String,
        userID: String
    ): Flow<List<MarketItemStateEntity>> =
        flow {
            val marketItemStateEntity: List<MarketItemStateEntity> = firestore
                .collection(USERS)
                .document(userID)
                .collection(MARKET_ITEM_STATE)
                .whereEqualTo("marketId", marketID)
                .whereEqualTo("enabled", true)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(MarketItemStateEntity::class.java) }

            emit(marketItemStateEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de marketItemState", e)
            emit(emptyList())
        }

    override suspend fun getByID(
        marketItemStateID: String,
        userID: String
    ): Flow<MarketItemStateEntity?> =
        flow {
            val marketItemStateEntity = firestore
                .collection(USERS)
                .document(userID)
                .collection(MARKET_ITEM_STATE)
                .document(marketItemStateID)
                .get()
                .await().toObject(MarketItemStateEntity::class.java)

            emit(marketItemStateEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de marketItemState", e)
            emit(null)
        }

    override suspend fun insert(
        marketItemStateEntity: MarketItemStateEntity,
        userID: String
    ) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(MARKET_ITEM_STATE)
            .document(marketItemStateEntity.id ?: throw IllegalArgumentException("MarketItemState ID should be not null"))
            .set(marketItemStateEntity)
    }

    override suspend fun deleteByID(
        marketItemStateID: String,
        userID: String
    ) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(MARKET_ITEM_STATE)
            .document(marketItemStateID)
            .delete()
    }

    override suspend fun deleteAllByMarketID(marketID: String, userID: String) {
        val all = getAllByMarketID(marketID, userID).firstOrNull()
        all?.forEach { marketItemStateEntity ->

            marketItemStateEntity.id?.let {
                firestore
                    .collection(USERS)
                    .document(userID)
                    .collection(MARKET_ITEM_STATE)
                    .document(it)
                    .delete()
            }
        }
    }

    override suspend fun getLastByName(
        name: String,
        userID: String
    ): Flow<MarketItemStateEntity?> =
        flow {
            val marketItemStateEntity: MarketItemStateEntity? = firestore
                .collection(USERS)
                .document(userID)
                .collection(MARKET_ITEM_STATE)
                .whereEqualTo("name", name)
                .get()
                .await()
                .documents
                .firstOrNull()?.toObject(MarketItemStateEntity::class.java)

            emit(marketItemStateEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de marketItemState", e)
            emit(null)
        }

    override suspend fun getLastByProductName(
        productName: String,
        userID: String
    ): Flow<MarketItemStateEntity?> =
        flow {
            val marketItemStateEntity: MarketItemStateEntity? = firestore
                .collection(USERS)
                .document(userID)
                .collection(MARKET_ITEM_STATE)
                .whereEqualTo("productName", productName)
                .whereEqualTo("enabled", false)
                .get()
                .await()
                .documents
                .firstOrNull()?.toObject(MarketItemStateEntity::class.java)

            emit(marketItemStateEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de marketItemState", e)
            emit(null)
        }
}