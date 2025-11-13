package com.sm.keepmarket.data.model

import com.google.firebase.Timestamp

data class PantryItemEntity(
    val id: String? = null,
    val pantryId: String? = null,
    val name: String? = null,
    val amount: Int? = null,
    val dueDate: Timestamp? = null,
)
