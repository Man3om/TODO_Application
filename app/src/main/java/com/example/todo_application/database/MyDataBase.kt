package com.example.todo_application.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo_application.database.dao.TasksDao
import com.example.todo_application.database.entity.Task
import com.example.todo_application.typeConverter.DataTypeConverter


@TypeConverters(value = [DataTypeConverter::class])
@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    companion object {
        private var instance: MyDataBase? = null
        private const val DATABASE_NAME = "Task"

        fun init(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, MyDataBase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration(true)
                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun getInstance(): MyDataBase {
            return instance!!
        }
    }
}