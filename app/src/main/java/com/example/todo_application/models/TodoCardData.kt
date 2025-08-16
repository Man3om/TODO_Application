package com.example.todo_application.models

data class TodoCardData(
    val title: String,
    val description: String,
    val time : String?,
    val isCompleted: Boolean
)
