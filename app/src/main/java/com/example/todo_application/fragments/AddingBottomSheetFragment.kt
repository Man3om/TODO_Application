package com.example.todo_application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo_application.R
import com.example.todo_application.databinding.FragmentAddingBottomSheetBinding
import com.example.todo_application.models.TodoCardData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddingBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentAddingBottomSheetBinding

    var saveTask: ((TodoCardData) -> Unit)? = null
    private lateinit var task: TodoCardData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddingBottomSheetBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = _binding.tasknameid.text.toString()
        val description = _binding.taskdescriptionid.text.toString()
        val isDone = false

        task = TodoCardData(title, description, null , isDone)

        _binding.AddingButton.setOnClickListener {
            saveTask?.invoke(task)
            dismiss()
        }
    }
}