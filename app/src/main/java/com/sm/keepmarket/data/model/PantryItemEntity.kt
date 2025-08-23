package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "TB_PANTRYITEM")
data class PantryItemEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "PANTRY_ID")
    val pantryId: String,
    @ColumnInfo(name = "NAME")
    val name: String,
    @ColumnInfo(name = "AMOUNT")
    val amount: Int,
    @ColumnInfo(name = "DUE_DATE")
    val dueDate: LocalDate
)
