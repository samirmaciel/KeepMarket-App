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
    fun getAll(): Flow<List<MarketItemStateEntity>>

    @Query("SELECT * FROM TB_MARKETITEMSTATE WHERE ID = :id AND ENABLED = 1")
    fun getByID(id: String): Flow<MarketItemStateEntity?>

    @Query("SELECT * FROM TB_MARKETITEMSTATE WHERE MARKETITEM_ID = :marketItemID AND ENABLED = 1")
    fun getByOwnerID(marketItemID: String): Flow<List<MarketItemStateEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(item: MarketItemStateEntity)

    @Delete
    fun delete(item: MarketItemStateEntity)


}