package com.example.todo_application.models

import java.util.Date

data class TodoCardData(
    var title: String,
    var description: String,
    var time : Date,
    var isCompleted: Boolean
)
