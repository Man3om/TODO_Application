package com.example.todo_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.todo_application.database.MyDataBase
import com.example.todo_application.database.entity.Task
import com.example.todo_application.databinding.FragmentEditTaskBinding

/**
 * A simple [androidx.fragment.app.Fragment] subclass.
 * Use the [EditTaskDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditTaskDialogFragment() : DialogFragment() {

    private val TAG = "EditTaskFragment"
    private lateinit var binding: FragmentEditTaskBinding

    lateinit var task: Task

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            updateTask()
            dismiss()
        }

    }

    private fun updateTask() {
        MyDataBase.getInstance().tasksDao().updateTask(task)
    }

}