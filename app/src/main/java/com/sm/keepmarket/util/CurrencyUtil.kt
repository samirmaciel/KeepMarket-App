package com.sm.keepmarket.util

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

object CurrencyUtil {


    fun formatterTextToCurrency(value: String, locale: Locale): String? {

        val formatted: String? = null
        val monetarySignal = Currency.getInstance(locale).symbol
        val cleanString = value.replace("[${monetarySignal},.]".toRegex(), "").replace("\\s+".toRegex(), "")

        if(cleanString.isNotEmpty()){

            try {
                val parsed : Double = cleanString.toDouble()
                val formatted = NumberFormat.getCurrencyInstance(locale).format(parsed / 100)
                return formatted
            }catch (e : NumberFormatException){

            }
        }

        return formatted
    }


    fun parseCurrencyToBigDecimal(value: String, locale: Locale): BigDecimal {
        val format = NumberFormat.getCurrencyInstance(locale)
        val number = format.parse(value) ?: 0
        return BigDecimal(number.toString())
    }

    fun bigDecimalToCurrency(value: BigDecimal, locale: Locale): String {
        val format = NumberFormat.getCurrencyInstance(locale)
        return format.format(value)
    }
}