package com.sm.keepmarket.data.db

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RoomConverter {

    @TypeConverter
    fun stringToLocalDateTime(value: String?): LocalDateTime? {
        return value?.let {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            val dateTime = LocalDateTime.parse(value, formatter)
            return dateTime
        }
    }

    @TypeConverter
    fun localDateTimeToString(localDateTime: LocalDateTime?): String? {
        return localDateTime?.let{
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            val formatted = localDateTime.format(formatter)
            return formatted
        }

    }

    @TypeConverter
    fun stringToLocalDate(value: String?): LocalDate? {
        return value?.let {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val dateTime = LocalDate.parse(value, formatter)
            return dateTime
        }
    }

    @TypeConverter
    fun localDateToString(localDate: LocalDate?): String? {
        return localDate?.let{
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val formatted = localDate.format(formatter)
            return formatted
        }

    }
}