package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "TB_PANTRY")
data class PantryEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "NAME")
    val name: String,
    @ColumnInfo(name = "CREATED_DATE")
    val createdDate: LocalDateTime,
    @ColumnInfo(name = "LAST_UPDATE")
    val lastUpdate: LocalDateTime
)
