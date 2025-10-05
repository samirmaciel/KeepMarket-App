package com.sm.keepmarket.domain.model

import com.sm.keepmarket.util.ValidationType

class ValidationModel(
    val id: String,
    val validationType: ValidationType,
    val description: String,
    var isValid: Boolean = false,
){

}


abstract class NumbersAndLettersValidation : IValidate{

    override fun validateValue(value: String): Boolean {
        return (value.contains(Regex("[a-z]")) && value.contains(Regex("[1-9]")))
    }
}


interface IValidate {
    fun validateValue(value: String) : Boolean
}