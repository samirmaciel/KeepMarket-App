package com.sm.keepmarket.domain

import androidx.annotation.DrawableRes
import com.sm.keepmarket.util.HighlightType

data class HighlightItem(
    val id: String,
    val title: String,
    val subTitle: String,
    val type: HighlightType,
    @DrawableRes val icon: Int,
    val description: String?
)
