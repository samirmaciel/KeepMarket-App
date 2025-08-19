package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "TB_PANTRYENTITY")
data class PantryEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "createdDate")
    val createdDate: LocalDateTime,
    @ColumnInfo(name = "lastUpdate")
    val lastUpdate: LocalDateTime
)
