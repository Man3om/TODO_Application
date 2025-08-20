package com.example.todo_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo_application.adapters.ToDoFragmentRecyclerViewAdapter
import com.example.todo_application.database.MyDataBase
import com.example.todo_application.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

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

        adapter = ToDoFragmentRecyclerViewAdapter(listOf())
        getAllTasks()
        binding.recyclerView.adapter = adapter
    }

    private fun getAllTasks() {
        val items = MyDataBase.getInstance().tasksDao().getAllTasks()
        adapter.setNewTaskList(items)
    }
}