package com.sm.keepmarket.data.model

import com.sm.keepmarket.domain.model.UserRegisterModel

data class UserRegisterEntity(
    val name: String,
    val password: String,
    val email: String
)

fun UserRegisterEntity.toUserRegisterModel() = UserRegisterModel(
    this.name,
    this.password,
    this.email
)
