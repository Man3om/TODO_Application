package com.example.todo_application.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.todo_application.databinding.TaskCardBinding
import com.example.todo_application.models.TodoCardData

class ToDoFragmentRecyclerViewAdapter(private val items: List<TodoCardData>) : RecyclerView.Adapter<ToDoFragmentRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = TaskCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemBinding: ViewBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item: TodoCardData , position: Int) {
            TODO("Not yet implemented")
        }
    }
}