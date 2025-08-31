package com.example.todo_application.typeConverter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class DataTypeConverter {

    @TypeConverter
    fun convertLocalDateToLong(localDate: LocalDate): Long {
        return localDate
            .atStartOfDay(ZoneId.systemDefault()) // Sets the time to 00:00 in the system's default time zone
            .toInstant()                        // Converts to a moment in time (Instant)
            .toEpochMilli()                     // Converts to milliseconds since the epoch
    }

    @TypeConverter
    fun convertLongToLocalDate(epochMillis: Long): LocalDate {
        return LocalDate.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.systemDefault())
    }

}