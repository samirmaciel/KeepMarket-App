package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_NOTIFICATIONENTITY")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "subTitle")
    val subTitle: String
)
