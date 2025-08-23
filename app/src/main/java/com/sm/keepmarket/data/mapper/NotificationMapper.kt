package com.sm.keepmarket.data.mapper

import com.sm.keepmarket.data.model.NotificationEntity
import com.sm.keepmarket.domain.model.Notification

class NotificationMapper() {

    fun toNotification(entity: NotificationEntity): Notification {
        return Notification(
            id = entity.id,
            title = entity.title,
            subTitle = entity.subTitle
        )
    }

    fun toEntity(notification: Notification): NotificationEntity {
        return NotificationEntity(
            id = notification.id,
            title = notification.title,
            subTitle = notification.subTitle
        )
    }

}