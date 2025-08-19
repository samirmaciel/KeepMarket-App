package com.sm.keepmarket.data.model

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sm.keepmarket.util.HighlightType


@Entity(tableName = "TB_HIGHLIGHTENTITY")
data class HighlightEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "subTitle")
    val subTitle: String,
    @ColumnInfo(name = "icon")
    @DrawableRes val icon: Int,
    @ColumnInfo(name = "infoText")
    val description: String,
    @ColumnInfo("highlightType")
    val type: HighlightType
)
