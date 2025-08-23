package com.sm.keepmarket.data.model

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sm.keepmarket.util.HighlightType


@Entity(tableName = "TB_HIGHLIGHT")
data class HighlightEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "TITLE")
    val title: String,
    @ColumnInfo(name = "SUBTITLE")
    val subTitle: String,
    @ColumnInfo(name = "ICON")
    @DrawableRes val icon: Int,
    @ColumnInfo(name = "INFOTEXT")
    val description: String,
    @ColumnInfo("HIGHLIGHT_TYPE")
    val type: HighlightType
)
