package com.sm.keepmarket.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class NotificationEntity(
    val id: String? = null,
    val title: String? = null,
    val subTitle: String? = null
)
