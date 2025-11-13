package com.sm.keepmarket.data.mapper

import com.google.firebase.Timestamp
import com.sm.keepmarket.data.model.PantryEntity
import com.sm.keepmarket.domain.model.Pantry
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

object PantryMapper {

    fun toPantry(entity: PantryEntity): Pantry {

        val createdDateTime = entity.createdDate?.toDate()
            ?.toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDateTime()

        val lastUpdate = entity.createdDate?.toDate()
            ?.toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDateTime()

        return Pantry(
            id = entity.id!!,
            name = entity.name!!,
            createdDate = createdDateTime!!,
            lastUpdate = lastUpdate!!
        )
    }

    fun toEntity(pantry: Pantry): PantryEntity {

        val createdDate = Timestamp(
            Date.from(
            LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()
        ))

        val lastUpdate = Timestamp(
            Date.from(
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()
            )
        )

        return PantryEntity(
            id = pantry.id,
            name = pantry.name,
            createdDate = createdDate,
            lastUpdate = lastUpdate
        )
    }
}