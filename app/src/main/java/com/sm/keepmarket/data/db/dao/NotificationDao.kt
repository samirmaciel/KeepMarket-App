package com.sm.keepmarket.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.sm.keepmarket.data.model.NotificationEntity

@Dao
interface NotificationDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(notificationEntity: NotificationEntity)

    @Delete
    suspend fun delete(notificationEntity: NotificationEntity)

    @Query("SELECT * FROM TB_NOTIFICATION")
    suspend fun getAll(): List<NotificationEntity>

    @Query("SELECT * FROM TB_NOTIFICATION WHERE ID = :id")
    suspend fun getById(id: String): NotificationEntity?

}