package com.sm.keepmarket.domain.model

import com.sm.keepmarket.data.model.UserEntity

data class UserModel(
    var uuid: String? = null,
    var name: String? = null,
    var email: String? = null
)

fun UserModel.toUserEntity() = UserEntity(
    this.uuid,
    this.name,
    this.email
)
