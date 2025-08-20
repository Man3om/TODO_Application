package com.example.todo_application.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.todo_application.R
import com.example.todo_application.database.MyDataBase
import com.example.todo_application.database.entity.Task
import com.example.todo_application.databinding.FragmentAddingBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddingBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentAddingBottomSheetBinding
    private lateinit var calendar: Calendar
    private val TAG = "AddingBottomSheetFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddingBottomSheetBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "AddingBottomSheetFragment Started")

        calendar = Calendar.getInstance()

        _binding.AddingButton.setOnClickListener {
            Log.d(TAG, "Save Button Clicked")
            saveTaskInDB()
        }
        _binding.PleaseDateTv.setOnClickListener {
            Log.d(TAG, "Select Date Clicked")
            showDatePicker()
        }
        _binding.PleaseTimeTv.setOnClickListener {
            Log.d(TAG, "Select Time Clicked")
            showTimePicker()
        }
    }

    private fun saveTaskInDB() {
        if (isValidData()) {
            val task = Task(
                title = _binding.tasknameid.text?.toString(),
                description = _binding.taskdescriptionid.text?.toString(),
                isCompleted = false,
                timeStamp = calendar.time
            )
            Log.d(TAG, "Task: $task Inserted Successfully")

            MyDataBase.getInstance().tasksDao().insertTask(task)
            dismiss()
        }
    }

    private fun isValidData(): Boolean {
        if (_binding.tasknameid.text.isNullOrEmpty()) {
            _binding.tasknameid.error = "Please Enter Task Name"
            return false
        }

        if (_binding.taskdescriptionid.text.isNullOrEmpty()) {
            _binding.taskdescriptionid.error = "Please Enter Task Description"
            return false
        }

        if (_binding.PleaseDateTv.text == getString(R.string.please_select_date)) {
            _binding.PleaseDateTv.error = "Please Select Date"
            return false
        }

        if (_binding.PleaseTimeTv.text == getString(R.string.please_select_time)) {
            _binding.PleaseTimeTv.error = "Please Select Time"
            return false
        }

        return true
    }

    private fun showDatePicker() {
        val dialog = DatePickerDialog(
            requireContext(),
            object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(
                    view: DatePicker?,
                    year: Int,
                    month: Int,
                    dayOfMonth: Int
                ) {
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    _binding.PleaseDateTv.text = "$dayOfMonth - $month - $year"
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()
    }

    private fun showTimePicker() {
        val dialog =
            TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(
                    view: android.widget.TimePicker?,
                    hourOfDay: Int,
                    minute: Int
                ) {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    _binding.PleaseTimeTv.text = "$hourOfDay : $minute"
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
        dialog.show()
    }
}