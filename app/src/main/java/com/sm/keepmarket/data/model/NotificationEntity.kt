package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_NOTIFICATION")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "TITLE")
    val title: String,
    @ColumnInfo(name = "SUBTITLE")
    val subTitle: String
)
