package com.sm.keepmarket.data.model

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.Timestamp
import com.sm.keepmarket.util.HighlightType



data class HighlightEntity(
    val id: String? = null,
    val title: String? = null,
    val subTitle: String? = null,
    @DrawableRes val icon: Int? = null,
    val description: String? = null,
    val type: HighlightType? = null,
    val createdDate: Timestamp? = null
)
