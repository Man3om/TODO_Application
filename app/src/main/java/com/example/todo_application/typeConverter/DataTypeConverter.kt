package com.example.todo_application.typeConverter

import androidx.room.TypeConverter
import java.util.Date

class DataTypeConverter {

    @TypeConverter
    fun convertDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun convertLongToDate(date: Long): Date {
        return Date(date)
    }

}