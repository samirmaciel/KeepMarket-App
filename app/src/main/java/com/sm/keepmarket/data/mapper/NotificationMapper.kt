package com.sm.keepmarket.data.mapper

import com.sm.keepmarket.data.model.NotificationEntity
import com.sm.keepmarket.domain.model.NotificationItem

object NotificationMapper {

    fun toNotification(entity: NotificationEntity): NotificationItem {
        return NotificationItem(
            id = entity.id!!,
            title = entity.title!!,
            subTitle = entity.subTitle!!
        )
    }

    fun toEntity(notificationItem: NotificationItem): NotificationEntity {
        return NotificationEntity(
            id = notificationItem.id,
            title = notificationItem.title,
            subTitle = notificationItem.subTitle
        )
    }

}