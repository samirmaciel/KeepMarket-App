package com.sm.keepmarket.data.mapper

import com.sm.keepmarket.data.model.HighlightEntity
import com.sm.keepmarket.domain.model.Highlight

class HighlightMapper() {

    fun toHighlight(entity: HighlightEntity): Highlight {
        return Highlight(
            id = entity.id,
            title = entity.title,
            subTitle = entity.subTitle,
            type = entity.type,
            icon = entity.icon,
            description = entity.description
        )
    }

    fun toEntity(highlight: Highlight): HighlightEntity {
        return HighlightEntity(
            id = highlight.id,
            title = highlight.title,
            subTitle = highlight.subTitle,
            type = highlight.type,
            icon = highlight.icon,
            description = highlight.description
        )
    }
}