package com.sm.keepmarket.data.model


import com.google.firebase.Timestamp

data class MarketItemStateEntity(
    val id: String? = null,
    val marketId: String? = null,
    val marketItemId: String? = null,
    val name: String? = null,
    val productName: String? = null,
    val price: String? = null,
    val amount: Double? = null,
    val unitType: String? = null,
    val checked: Boolean? = null,
    val lastUpdate: Timestamp? = null,
    val createdDate: Timestamp? = null,
)
