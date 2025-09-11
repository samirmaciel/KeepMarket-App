package com.sm.keepmarket.domain.model

import com.sm.keepmarket.util.FeaturedType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

data class Pantry(
    val id: String,
    val name: String,
    val createdDate: LocalDateTime,
    var lastUpdate: LocalDateTime,
    var items: List<PantryItem> = emptyList()
){
    fun updateLastUpdate(){
        lastUpdate = LocalDateTime.now()
    }

    fun getFormattedLastUpdate(): String{
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss")
        val formatted = lastUpdate.format(formatter)

        return formatted
    }
}
