package com.sm.keepmarket.data.mapper

import com.google.firebase.Timestamp
import com.sm.keepmarket.data.model.PantryItemEntity
import com.sm.keepmarket.domain.model.PantryItem
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

object PantryItemMapper {


    fun toPantryItem(entity: PantryItemEntity): PantryItem {

        val dueDate = entity.dueDate?.toDate()
            ?.toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDate()


        return PantryItem(
            id = entity.id!!,
            pantryId = entity.pantryId!!,
            name = entity.name!!,
            amount = entity.amount!!,
            dueDate = dueDate!!
        )
    }

    fun toEntity(pantryItem: PantryItem): PantryItemEntity {

        val dueDate = Timestamp(
            Date.from(
                pantryItem.dueDate
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
            )
        )

        return PantryItemEntity(
            id = pantryItem.id,
            pantryId = pantryItem.pantryId,
            name = pantryItem.name,
            amount = pantryItem.amount,
            dueDate = dueDate
        )
    }

}