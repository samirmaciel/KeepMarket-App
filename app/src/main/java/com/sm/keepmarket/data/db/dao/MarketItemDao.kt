package com.sm.keepmarket.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.sm.keepmarket.data.model.MarketItemEntity

@Dao
interface MarketItemDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(marketItemEntity: MarketItemEntity)

    @Delete
    suspend fun delete(marketItemEntity: MarketItemEntity)

    @Query("SELECT * FROM TB_MARKETITEM")
    suspend fun getAll(): List<MarketItemEntity>

    @Query("SELECT * FROM TB_MARKETITEM WHERE MARKET_ID = :ownerID")
    suspend fun getAllByOwner(ownerID: String): List<MarketItemEntity>

    @Query("SELECT * FROM TB_MARKETITEM WHERE ID = :id")
    suspend fun getById(id: String): MarketItemEntity?

}