package com.sm.keepmarket.domain

import com.sm.keepmarket.util.FeatureType
import java.time.LocalDateTime

data class FeaturedCard(
    val id: String,
    val name: String,
    val featureType: FeatureType,
    val lastUpdate: LocalDateTime
)
