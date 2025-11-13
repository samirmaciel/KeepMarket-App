package com.sm.keepmarket.data.model


import com.google.firebase.Timestamp


data class MarketEntity(
    val id: String? = null,
    val name: String? = null,
    val createdDate: Timestamp? = null,
    val lastUpdate: Timestamp? = null
)
