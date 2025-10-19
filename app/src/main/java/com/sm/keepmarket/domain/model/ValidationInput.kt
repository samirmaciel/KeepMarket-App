package com.sm.keepmarket.domain.model

import com.sm.keepmarket.util.ValidationType

object ValidationInput {

    fun getValidation(value: String, type: ValidationType): Boolean {
        return when(type){
            ValidationType.NUMBERS_AND_LETTERS -> validateNumbersAndLetters(value)
            ValidationType.ONE_SPECIAL_CHARACTER -> validateOnSpecialCharacter(value)
            ValidationType.MORE_OR_EQUAL_THAN_8_CHARACTERS -> validateMoreThan8Characters(value)
            ValidationType.ONE_CAPITAL_LETTER -> validateOneCapitalCharacter(value)
            ValidationType.IS_NOT_EMPTY -> value.isNotEmpty()
        }
    }

    private fun validateMoreThan8Characters(value: String): Boolean {
        return value.length >= 8
    }

    private fun validateNumbersAndLetters(value: String): Boolean {
        return (value.contains(Regex("[a-z]")) && value.contains(Regex("[1-9]")))
    }

    private fun validateOnSpecialCharacter(value: String): Boolean {
        return value.contains(Regex("[^\\p{L}\\p{N}]"))
    }

    private fun validateOneCapitalCharacter(value: String): Boolean {
        return value.contains(Regex("[A-Z]"))
    }
}