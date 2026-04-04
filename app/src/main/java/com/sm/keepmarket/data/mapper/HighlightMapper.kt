package com.sm.keepmarket.data.mapper

import com.google.firebase.Timestamp
import com.sm.keepmarket.data.model.HighlightEntity
import com.sm.keepmarket.domain.model.Highlight
import java.time.ZoneId
import java.util.Date

object HighlightMapper {

    fun toHighlight(entity: HighlightEntity): Highlight {

        val createdDateTime = entity.createdDate?.toDate()
            ?.toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDateTime()

        return Highlight(
            id = entity.id!!,
            title = entity.title!!,
            subTitle = entity.subTitle!!,
            type = entity.type!!,
            icon = entity.icon!!,
            description = entity.description!!,
            createdDate = createdDateTime!!
        )
    }

    fun toEntity(highlight: Highlight): HighlightEntity {

        val createdDate = Timestamp(
            Date.from(
                highlight.createdDate.atZone(ZoneId.systemDefault()).toInstant()
            ))

        return HighlightEntity(
            id = highlight.id,
            title = highlight.title,
            subTitle = highlight.subTitle,
            type = highlight.type,
            icon = highlight.icon,
            description = highlight.description,
            createdDate = createdDate
        )
    }
}