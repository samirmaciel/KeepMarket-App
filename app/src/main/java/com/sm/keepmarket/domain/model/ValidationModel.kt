package com.sm.keepmarket.domain.model

import com.sm.keepmarket.util.ValidationType

class ValidationModel(
    val validationType: ValidationType,
    val description: String,
    var isValid: Boolean = false,
)