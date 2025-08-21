package com.example.todo_application.adapters

import android.view.View
import com.example.todo_application.databinding.CalendarWeekdayLayoutBinding
import com.kizitonwose.calendar.view.ViewContainer

class WeekDayViewContainer(view: View) : ViewContainer(view) {
    val numDayTv = CalendarWeekdayLayoutBinding.bind(view).daynumberTv
    val dayNameTv = CalendarWeekdayLayoutBinding.bind(view).dayTv
}