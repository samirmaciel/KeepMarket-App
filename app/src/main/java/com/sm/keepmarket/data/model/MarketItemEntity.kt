package com.sm.keepmarket.data.model

import com.google.firebase.Timestamp
data class MarketItemEntity(
    val id: String? = null,
    val ownerID: String? = null,
    val name: String? = null,
    val productName: String? = null,
    val createdDate: Timestamp? = null,
)
