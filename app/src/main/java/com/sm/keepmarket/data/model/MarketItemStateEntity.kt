package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_MARKETITEMSTATE")
data class MarketItemStateEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "MARKETITEM_ID")
    val marketItemId: String,
    @ColumnInfo(name = "NAME")
    val name: String,
    @ColumnInfo(name = "PRICE")
    val price: String,
    @ColumnInfo(name = "AMOUNT")
    val amount: Int,
    @ColumnInfo(name = "LAST_UPDATE")
    val lastUpdate: String,
    @ColumnInfo(name = "CREATED_DATE")
    val createdDate: String,
    @ColumnInfo(name = "ENABLED")
    val enabled: Boolean
)
