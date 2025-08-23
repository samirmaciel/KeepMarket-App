package com.sm.keepmarket.domain.model

import androidx.compose.ui.graphics.Color
import com.sm.keepmarket.presentation.theme.Green
import com.sm.keepmarket.presentation.theme.Orange
import com.sm.keepmarket.presentation.theme.Red
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class PantryItem(val id: String, val pantryId: String, val name: String, var amount: Int = 0, val dueDate: LocalDate) {

    fun getDueDateFormatted() = dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yy"))

    fun getTimeLeft(): String {

        val today = LocalDate.now()
        val period = Period.between(today, dueDate)

        val years = period.years
        val months = period.months
        val days = period.days

        return when {
            years > 0 -> "$years years to expiration"
            months > 0 -> "$months months to expiration"
            days > 0 -> "$days days to expiration"
            else -> {"Expired"}
        }
    }

    fun getDueColor(): Color{
        val today = LocalDate.now()
        val period = Period.between(today, dueDate)

        if(amount == 0){
            return Color.LightGray
        }

        if(period.days <= 0){
            return Red
        }

        if(period.days < 4){
            return Orange
        }

        return Green
    }
}