package com.sm.keepmarket.domain

import java.time.LocalDateTime

data class FeaturedCard(
    val id: String,
    val type: String,
    val title: String,
    val route: Route,
    val date: LocalDateTime

)
