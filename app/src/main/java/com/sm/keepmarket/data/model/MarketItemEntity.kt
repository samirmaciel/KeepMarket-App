package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "TB_MARKETITEM")
data class MarketItemEntity(
    @PrimaryKey (autoGenerate = false) @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "MARKET_ID")
    val ownerID: String,
    @ColumnInfo(name = "NAME")
    val name: String,
    @ColumnInfo(name = "CREATED_DATE")
    val createdDate: LocalDateTime
)
