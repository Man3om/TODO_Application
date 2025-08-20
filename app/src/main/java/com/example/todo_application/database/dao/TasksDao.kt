package com.example.todo_application.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo_application.database.entity.Task


@Dao
interface TasksDao {
    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM Task")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM Task WHERE timeStamp = :date")
    fun getAllTasksByDate(date: Long): List<Task>

    @Query("SELECT * FROM Task WHERE id = :id")
    fun getTaskById(id: Int): Task?

    @Query("SELECT * FROM Task WHERE isCompleted = :isCompleted")
    fun getTasksByCompletionStatus(isCompleted: Boolean): List<Task>
}