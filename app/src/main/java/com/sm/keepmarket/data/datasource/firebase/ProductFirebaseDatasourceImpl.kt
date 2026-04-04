package com.sm.keepmarket.data.datasource.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.sm.keepmarket.data.datasource.datasourceInterface.IProductDatasource
import com.sm.keepmarket.data.model.MarketItemStateEntity
import com.sm.keepmarket.data.model.ProductEntity
import com.sm.keepmarket.domain.FireStoreCollections.MARKET_ITEM_STATE
import com.sm.keepmarket.domain.FireStoreCollections.PRODUCTS
import com.sm.keepmarket.domain.FireStoreCollections.USERS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ProductFirebaseDatasourceImpl(private val firestore: FirebaseFirestore) : IProductDatasource {

    private val TAG = this.javaClass.name

    override suspend fun getByID(
        id: String,
        userID: String
    ): Flow<ProductEntity?> =
        flow {
            val productEntity = firestore
                .collection(USERS)
                .document(userID)
                .collection(PRODUCTS)
                .document(id)
                .get()
                .await().toObject(ProductEntity::class.java)

            emit(productEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar productEntity pelo id", e)
            emit(null)
        }

    override suspend fun getLast(
        name: String,
        userID: String
    ): Flow<ProductEntity?> =
        flow {
            val productEntity: ProductEntity? = firestore
                .collection(USERS)
                .document(userID)
                .collection(PRODUCTS)
                .whereEqualTo("name", name)
                .orderBy("createdDate", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .await()
                .documents
                .firstOrNull()?.toObject(ProductEntity::class.java)

            emit(productEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar o ultimo de productEntity", e)
            emit(null)
        }

    override suspend fun insert(
        productEntity: ProductEntity,
        userID: String
    ) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(PRODUCTS)
            .document(productEntity.id ?: throw IllegalArgumentException("productEntity ID should be not null"))
            .set(productEntity)
    }

    override suspend fun delete(
        id: String,
        userID: String
    ) {
        firestore
            .collection(USERS)
            .document(userID)
            .collection(PRODUCTS)
            .document(id)
            .delete()
    }

    override suspend fun getAllByMarketItemParent(
        marketItemParent: String,
        userID: String
    ): Flow<List<ProductEntity>> =
        flow {
            val productEntityList: List<ProductEntity> = firestore
                .collection(USERS)
                .document(userID)
                .collection(PRODUCTS)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(ProductEntity::class.java) }

            emit(productEntityList)
        }.catch { e ->
            Log.e(TAG, "Erro ao carregar lista de productEntity", e)
            emit(emptyList())
        }
}