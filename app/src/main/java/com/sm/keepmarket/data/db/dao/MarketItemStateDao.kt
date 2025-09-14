package com.sm.keepmarket.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.sm.keepmarket.data.model.MarketItemStateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarketItemStateDao {

    @Query("SELECT * FROM TB_MARKETITEMSTATE WHERE ENABLED = 1")
    suspend fun getAll(): List<MarketItemStateEntity>

    @Query("SELECT * FROM TB_MARKETITEMSTATE WHERE ID = :id AND ENABLED = 1")
    suspend fun getByID(id: String): MarketItemStateEntity?

    @Query("SELECT * FROM TB_MARKETITEMSTATE WHERE MARKET_ID = :marketId AND ENABLED = 1")
    suspend fun getByOwnerID(marketId: String): List<MarketItemStateEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: MarketItemStateEntity)

    @Delete
    suspend fun delete(item: MarketItemStateEntity)


}