package com.sm.keepmarket.domain.model

import com.sm.keepmarket.util.FeaturedType
import java.time.LocalDateTime

data class FeaturedCard(
    val name: String,
    val featuredType: FeaturedType,
    val lastUpdate: LocalDateTime
)
