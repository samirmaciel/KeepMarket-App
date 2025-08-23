package com.sm.keepmarket.data.mapper

import com.sm.keepmarket.data.model.PantryItemEntity
import com.sm.keepmarket.domain.model.PantryItem

class PantryItemMapper() {


    fun toPantryItem(entity: PantryItemEntity): PantryItem {
        return PantryItem(
            id = entity.id,
            pantryId = entity.pantryId,
            name = entity.name,
            amount = entity.amount,
            dueDate = entity.dueDate
        )
    }

    fun toEntity(pantryItem: PantryItem): PantryItemEntity {
        return PantryItemEntity(
            id = pantryItem.id,
            pantryId = pantryItem.pantryId,
            name = pantryItem.name,
            amount = pantryItem.amount,
            dueDate = pantryItem.dueDate
        )
    }

}