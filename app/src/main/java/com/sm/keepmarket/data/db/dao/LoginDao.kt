package com.sm.keepmarket.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.sm.keepmarket.data.model.LoginEntity

@Dao
interface LoginDao {

    @Query("SELECT * FROM TB_LOGIN WHERE ENABLED = 1")
    suspend fun getAll(): List<LoginEntity>

    @Query("SELECT * FROM TB_LOGIN WHERE ENABLED = 1 ORDER BY CREATED_DATE DESC LIMIT 1")
    suspend fun getCurrentLogin(): LoginEntity?

    @Query("SELECT * FROM TB_LOGIN WHERE LOGIN = :name AND ENABLED = 1")
    suspend fun getLoginByName(name: String): LoginEntity?

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: LoginEntity)

    @Delete
    suspend fun delete(item: LoginEntity)


}