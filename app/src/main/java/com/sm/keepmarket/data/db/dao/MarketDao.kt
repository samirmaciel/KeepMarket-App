package com.sm.keepmarket.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.sm.keepmarket.data.model.MarketEntity
import com.sm.keepmarket.data.model.MarketItemEntity

@Dao
interface MarketDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(marketEntity: MarketEntity)

    @Delete
    suspend fun delete(marketEntity: MarketEntity)

    @Query("SELECT * FROM TB_MARKET")
    suspend fun getAll(): List<MarketEntity>

    @Query("SELECT * FROM TB_MARKET WHERE ID = :id")
    suspend fun getById(id: String): MarketEntity?

}