package com.example.todo_application.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo_application.database.MyDataBase
import com.example.todo_application.database.entity.Task
import com.example.todo_application.databinding.FragmentAddingBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddingBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentAddingBottomSheetBinding
    private var date: String? = null
    private var time: Long? = null
    private val TAG = "AddingBottomSheetFragment"
    private lateinit var task: Task

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
        saveTaskInDB()
        showTimePicker()
        showDatePicker()
    }

    private fun saveTaskInDB() {
        _binding.AddingButton.setOnClickListener {
            Log.d(TAG, "Save Button Clicked")

            val title = _binding.tasknameid.text.toString()
            val description = _binding.taskdescriptionid.text.toString()
            val isDone = false

            task = Task(
                title = title,
                description = description,
                isCompleted = isDone,
                date = date,
                time = time
            )
            Log.d(TAG, "Task: $task Inserted Successfully")

            MyDataBase.getInstance().tasksDao().insertTask(task)
            dismiss()
        }
    }

    private fun showDatePicker() {
        _binding.PleaseDateTv.setOnClickListener {
            Log.d(TAG, "Please Date textview Clicked")
            val datePicker = DatePickerDialog(requireContext())
            datePicker.show()

            datePicker.setOnDateSetListener { _, year, month, dayOfMonth ->
                _binding.PleaseDateTv.text = "$dayOfMonth/${month + 1}/$year"
                date = "$dayOfMonth/${month + 1}/$year"
            }
        }
    }
    private fun showTimePicker() {
        TODO("Not yet implemented")
    }
}