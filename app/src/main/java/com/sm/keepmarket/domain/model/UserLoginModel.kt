package com.sm.keepmarket.domain.model

import com.sm.keepmarket.data.model.UserLoginEntity

data class UserLoginModel(
    val email: String,
    val password: String
)

fun UserLoginModel.toUserLoginEntity() = UserLoginEntity(
    this.email,
    this.password
)
