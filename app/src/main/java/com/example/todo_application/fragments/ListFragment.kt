package com.example.todo_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo_application.adapters.ToDoFragmentRecyclerViewAdapter
import com.example.todo_application.adapters.WeekDayViewContainer
import com.example.todo_application.database.MyDataBase
import com.example.todo_application.databinding.FragmentListBinding
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.WeekCalendarView
import com.kizitonwose.calendar.view.WeekDayBinder
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var calendarView: WeekCalendarView
    private lateinit var adapter: ToDoFragmentRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView = binding.weekCalendar
        calendarView.dayBinder = object : WeekDayBinder<WeekDayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) = WeekDayViewContainer(view)

            // Called every time we need to reuse a container.
            override fun bind(container: WeekDayViewContainer, data: WeekDay) {
                container.numDayTv.text = data.date.dayOfMonth.toString()
                container.dayNameTv.text = data.date.dayOfWeek.name.take(3) // Example: "MON"
            }
        }

        initCalenderView()
        adapter = ToDoFragmentRecyclerViewAdapter(listOf())
        getAllTasks()
        binding.recyclerView.adapter = adapter
    }

    private fun initCalenderView() {
        val currentDate = LocalDate.now()
        val currentMonth = YearMonth.now()
        val startDate = currentMonth.minusMonths(24).atStartOfMonth() // Adjust as needed
        val endDate = currentMonth.plusMonths(24).atEndOfMonth() // Adjust as needed
        val daysOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.SATURDAY) // Available from the library
        calendarView.setup(startDate, endDate, daysOfWeek.first())
        calendarView.scrollToWeek(currentDate)
    }

    private fun getAllTasks() {
        val items = MyDataBase.getInstance().tasksDao().getAllTasks()
        adapter.setNewTaskList(items)
    }
}