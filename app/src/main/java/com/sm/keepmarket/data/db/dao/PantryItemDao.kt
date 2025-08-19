package com.sm.keepmarket.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.sm.keepmarket.data.model.PantryItemEntity

@Dao
interface PantryItemDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(pantryItemEntity: PantryItemEntity)

    @Delete
    suspend fun delete(pantryItemEntity: PantryItemEntity)

    @Query("SELECT * FROM TB_PANTRYITEMENTITY")
    suspend fun getAll(): List<PantryItemEntity>

    @Query("SELECT * FROM TB_PANTRYITEMENTITY WHERE pantryId = :ownerID")
    suspend fun getAllByOwner(ownerID: String): List<PantryItemEntity>

    @Query("SELECT * FROM TB_PANTRYITEMENTITY WHERE id = :id")
    suspend fun getById(id: String): PantryItemEntity?

}