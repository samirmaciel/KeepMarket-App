package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(tableName = "TB_MARKETITEMSTATE")
data class MarketItemStateEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "MARKET_ID")
    val marketId: String,
    @ColumnInfo(name = "MARKETITEM_ID")
    val marketItemId: String,
    @ColumnInfo(name = "NAME")
    val name: String,
    @ColumnInfo(name = "PRICE")
    val price: BigDecimal,
    @ColumnInfo(name = "AMOUNT")
    val amount: Int,
    @ColumnInfo(name = "IS_CHECKED")
    val isChecked: Boolean,
    @ColumnInfo(name = "LAST_UPDATE")
    val lastUpdate: LocalDateTime,
    @ColumnInfo(name = "CREATED_DATE")
    val createdDate: LocalDateTime,
    @ColumnInfo(name = "ENABLED")
    val enabled: Boolean
)
