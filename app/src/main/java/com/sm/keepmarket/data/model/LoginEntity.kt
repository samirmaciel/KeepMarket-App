package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "TB_LOGIN")
data class LoginEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "LOGIN")
    val login: String,
    @ColumnInfo(name = "PASSWORD")
    val password: String,
    @ColumnInfo(name = "CREATED_DATE")
    val createdDate: LocalDateTime,
    @ColumnInfo(name = "ENABLED")
    val enabled: Boolean,
)
