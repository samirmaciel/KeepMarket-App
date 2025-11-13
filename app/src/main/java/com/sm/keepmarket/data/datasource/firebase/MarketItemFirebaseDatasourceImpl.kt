package com.sm.keepmarket.data.datasource.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.model.MarketItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class MarketItemFirebaseDatasourceImpl(private val firestore: FirebaseFirestore): IMarketItemDatasource {

    private val TAG = this.javaClass.name

    override suspend fun getAllByUserID(userID: String): Flow<List<MarketItemEntity>> =
        flow {
            val marketItemEntityList: List<MarketItemEntity> = firestore
                .collection("USERS")
                .document(userID)
                .collection("MARKET_ITEM")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(MarketItemEntity::class.java) }

            emit(marketItemEntityList)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de marketItem", e)
            emit(emptyList())
        }


    override suspend fun getAllByMarketID(marketID: String, userID: String): Flow<List<MarketItemEntity>> =
        flow {
            val marketItemEntity: List<MarketItemEntity> = firestore
                .collection("USERS")
                .document(userID)
                .collection("MARKET_ITEM")
                .whereEqualTo("ownerID", marketID)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(MarketItemEntity::class.java) }

            emit(marketItemEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de marketItem", e)
            emit(emptyList())
        }


    override suspend fun getById(marketItemID: String, userID: String): Flow<MarketItemEntity?> =
        flow {
            val marketItemEntity = firestore
                .collection("USERS")
                .document(userID)
                .collection("MARKET_ITEM")
                .document(marketItemID)
                .get()
                .await().toObject(MarketItemEntity::class.java)

            emit(marketItemEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de marketItem", e)
            emit(null)
        }

    override suspend fun deleteByID(marketItemID: String, userID: String) {
        firestore
            .collection("USERS")
            .document(userID)
            .collection("MARKET_ITEM")
            .document(marketItemID)
            .delete()
    }

    override suspend fun deleteAllByMarketID(marketID: String, userID: String) {

        val all = getAllByMarketID(marketID, userID).firstOrNull()
        all?.forEach { marketItemEntity ->

            marketItemEntity.id?.let {
                firestore
                    .collection("USERS")
                    .document(userID)
                    .collection("MARKET_ITEM")
                    .document(it)
                    .delete()
            }
        }
    }

    override suspend fun insert(marketItemEntity: MarketItemEntity, userID: String) {
        firestore
            .collection("USERS")
            .document(userID)
            .collection("MARKET_ITEM")
            .document(marketItemEntity.id ?: throw IllegalArgumentException("MarketItem ID should be not null"))
            .set(marketItemEntity)
    }
}