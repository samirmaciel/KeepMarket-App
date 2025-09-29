package com.sm.keepmarket.domain.model

import java.time.LocalDateTime

data class LoginModel(
    val id: String,
    val login: String,
    val password: String,
    val createdDate: LocalDateTime,
    val enabled: Boolean
)
