package com.sm.keepmarket.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.sm.keepmarket.data.model.PantryEntity


@Dao
interface PantryDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(pantryEntity: PantryEntity)

    @Delete
    suspend fun delete(pantryEntity: PantryEntity)

    @Query("SELECT * FROM TB_PANTRY")
    suspend fun getAll(): List<PantryEntity>

    @Query("SELECT * FROM TB_PANTRY WHERE ID = :id")
    suspend fun getById(id: String): PantryEntity?

}