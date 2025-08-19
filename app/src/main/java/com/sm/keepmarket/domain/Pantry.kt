package com.sm.keepmarket.domain

data class Pantry(
    val id: String,
    val name: String,
    var items: List<PantryItem> = emptyList()
)
