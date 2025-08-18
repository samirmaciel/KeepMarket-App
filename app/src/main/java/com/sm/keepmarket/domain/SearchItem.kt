package com.sm.keepmarket.domain

data class SearchItem(
    val id: String,
    val title: String,
    val items: List<Highlight>
)
