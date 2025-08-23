package com.sm.keepmarket.domain.model

import com.sm.keepmarket.util.FeaturedType
import java.time.LocalDateTime
import java.util.UUID

data class Pantry(
    val id: String,
    val name: String,
    val createdDate: LocalDateTime,
    var lastUpdate: LocalDateTime,
    var items: List<PantryItem> = emptyList()
)
