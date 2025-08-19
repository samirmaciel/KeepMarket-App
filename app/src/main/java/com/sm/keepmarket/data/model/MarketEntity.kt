package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "TB_MARKETENTITY")
data class MarketEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val name: String,
    @ColumnInfo(name = "createdDate")
    val createdDate: LocalDateTime,
    @ColumnInfo(name = "lastUpdate")
    val lastUpdate: LocalDateTime
)
