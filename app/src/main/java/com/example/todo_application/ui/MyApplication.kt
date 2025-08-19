package com.example.todo_application.ui

import android.app.Application
import com.example.todo_application.database.MyDataBase

class MyApplication : Application()  {

    override fun onCreate() {
        super.onCreate()
        MyDataBase.init(this)
    }
}