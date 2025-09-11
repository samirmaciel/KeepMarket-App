package com.sm.keepmarket.domain.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Market(
    val id: String,
    val name: String,
    val createdDate: LocalDateTime,
    var lastUpdate: LocalDateTime,
    var items: List<MarketItem> = emptyList()
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
