package com.sm.keepmarket.domain.model

import com.sm.keepmarket.util.FeaturedType
import java.time.LocalDateTime

data class Pantry(
    val id: String,
    val name: String,
    val featuredType: FeaturedType,
    var lastUpdate: LocalDateTime,
    var items: List<PantryItem> = emptyList()

)
