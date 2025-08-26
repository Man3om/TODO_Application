package com.example.todo_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.todo_application.R
import com.example.todo_application.adapters.ToDoFragmentRecyclerViewAdapter
import com.example.todo_application.adapters.WeekDayViewContainer
import com.example.todo_application.adapters.WeekHeaderViewContainer
import com.example.todo_application.database.MyDataBase
import com.example.todo_application.databinding.CalendarWeekdayLayoutBinding
import com.example.todo_application.databinding.CalendarWeekheaderLayoutBinding
import com.example.todo_application.databinding.FragmentListBinding
import com.kizitonwose.calendar.core.Week
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.WeekCalendarView
import com.kizitonwose.calendar.view.WeekDayBinder
import com.kizitonwose.calendar.view.WeekHeaderFooterBinder
import java.sql.Date
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var calendarView: WeekCalendarView
    private lateinit var adapter: ToDoFragmentRecyclerViewAdapter

    private var selectedDate: LocalDate? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ToDoFragmentRecyclerViewAdapter(listOf())
        getTasksByDate(null)
        binding.recyclerView.adapter = adapter

        initCalenderView()
    }

    private fun initCalenderView() {
        calendarView = binding.weekCalendar

        calendarView.weekHeaderBinder = object : WeekHeaderFooterBinder<WeekHeaderViewContainer> {
            override fun create(view: View): WeekHeaderViewContainer {
                return WeekHeaderViewContainer(CalendarWeekheaderLayoutBinding.bind(view))
            }

            override fun bind(
                container: WeekHeaderViewContainer,
                data: Week
            ) {
                container.binding.monthNameTv.text = data.days[0].date.month.name
            }

        }

        calendarView.dayBinder = object : WeekDayBinder<WeekDayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) =
                WeekDayViewContainer(CalendarWeekdayLayoutBinding.bind(view))

            // Called every time we need to reuse a container.
            override fun bind(container: WeekDayViewContainer, data: WeekDay) {
                val weekDayTextView = container.binding.dayTv
                val monthDayTextView = container.binding.daynumberTv

                monthDayTextView.text = data.date.dayOfMonth.toString()
                weekDayTextView.text =
                    data.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())

                if (selectedDate == data.date) {
                    monthDayTextView.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.light_blue,
                            null
                        )
                    )

                    weekDayTextView.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.light_blue,
                            null
                        )
                    )
                } else {
                    monthDayTextView.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.black,
                            null
                        )
                    )

                    weekDayTextView.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.black,
                            null
                        )
                    )
                }

                container.binding.root.setOnClickListener {
                    val temp = selectedDate
                    selectedDate = data.date
                    calendarView.notifyDayChanged(data)
                    temp?.let {
                        calendarView.notifyWeekChanged(it)
                    }

                    getTasksByDate(data.date)
                }
            }
        }

        val currentDate = LocalDate.now()
        val currentMonth = YearMonth.now()
        val startDate = currentMonth.minusMonths(24).atStartOfMonth() // Adjust as needed
        val endDate = currentMonth.plusMonths(24).atEndOfMonth() // Adjust as needed
        val daysOfWeek =
            daysOfWeek(firstDayOfWeek = DayOfWeek.SATURDAY) // Available from the library
        calendarView.setup(startDate, endDate, daysOfWeek.first())
        calendarView.scrollToWeek(currentDate)
    }

    private fun getTasksByDate(date: Date?) {

        val items = if (date == null)
            MyDataBase.getInstance().tasksDao().getAllTasks()
        else
            MyDataBase.getInstance().tasksDao().getAllTasksByDate(date)

        adapter.setNewTaskList(items)
    }
}