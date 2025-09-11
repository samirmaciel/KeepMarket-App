package com.sm.keepmarket.domain.model

import com.sm.keepmarket.util.FeaturedType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class FeaturedCard(
    val id: String,
    val name: String,
    val featuredType: FeaturedType,
    var lastUpdate: LocalDateTime
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
