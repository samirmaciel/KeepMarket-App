package com.sm.keepmarket.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.sm.keepmarket.data.model.HighlightEntity

@Dao
interface HighlightDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(highlightEntity: HighlightEntity)

    @Delete
    suspend fun delete(highlightEntity: HighlightEntity)

    @Query("SELECT * FROM TB_HIGHLIGHT")
    suspend fun getAll(): List<HighlightEntity>

    @Query("SELECT * FROM TB_HIGHLIGHT WHERE ID = :id")
    suspend fun getById(id: String): HighlightEntity?

}