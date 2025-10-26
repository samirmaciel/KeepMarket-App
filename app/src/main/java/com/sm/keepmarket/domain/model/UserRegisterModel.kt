package com.sm.keepmarket.domain.model

import com.sm.keepmarket.data.model.UserRegisterEntity

data class UserRegisterModel(
    val name: String,
    val password: String,
    val email: String
)

fun UserRegisterModel.toUserRegisterEntity() = UserRegisterEntity(
    this.name,
    this.password,
    this.email
)
