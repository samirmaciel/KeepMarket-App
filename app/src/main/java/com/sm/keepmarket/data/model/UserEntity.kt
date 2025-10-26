package com.sm.keepmarket.data.model

import com.sm.keepmarket.domain.model.UserModel

data class UserEntity(
    var uuid: String? = null,
    var name: String? = null,
    var email: String? = null
)

fun UserEntity.toUserModel(): UserModel = UserModel(
    this.uuid,
    this.name,
    this.email
)
