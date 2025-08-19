package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(tableName = "TB_MARKETITEMENTITY")
data class MarketItemEntity(
    @PrimaryKey (autoGenerate = false) @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "marketId")
    val marketId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "unitPrice")
    val unitPrice: BigDecimal,
    @ColumnInfo(name = "totalPrice")
    val totalPrice: BigDecimal,
    @ColumnInfo(name = "amount")
    val amount: Int,
    @ColumnInfo(name = "createdDate")
    val createdDate: LocalDateTime
)
