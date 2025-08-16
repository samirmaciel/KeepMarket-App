package com.sm.keepmarket.domain

import androidx.annotation.DrawableRes

data class Highlight(
    val id: String,
    val title: String,
    val subTitle: String,
    @DrawableRes val icon: Int,
    val infoText: String?
)
