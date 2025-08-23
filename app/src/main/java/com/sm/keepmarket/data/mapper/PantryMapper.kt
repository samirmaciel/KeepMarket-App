package com.sm.keepmarket.data.mapper

import com.sm.keepmarket.data.model.PantryEntity
import com.sm.keepmarket.domain.model.Pantry

class PantryMapper() {

    fun toPantry(entity: PantryEntity): Pantry {
        return Pantry(
            id = entity.id,
            name = entity.name,
            createdDate = entity.createdDate,
            lastUpdate = entity.lastUpdate
        )
    }

    fun toEntity(pantry: Pantry): PantryEntity {
        return PantryEntity(
            id = pantry.id,
            name = pantry.name,
            createdDate = pantry.createdDate,
            lastUpdate = pantry.lastUpdate
        )
    }
}