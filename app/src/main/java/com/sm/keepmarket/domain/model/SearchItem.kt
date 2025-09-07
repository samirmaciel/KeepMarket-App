package com.sm.keepmarket.domain.model

data class SearchItem(
    val id: String,
    val title: String,
    val items: List<Highlight>,
    var expanded: Boolean = true
)
