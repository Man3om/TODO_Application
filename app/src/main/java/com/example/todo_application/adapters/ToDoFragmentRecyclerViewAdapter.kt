package com.example.todo_application.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_application.database.MyDataBase
import com.example.todo_application.database.entity.Task
import com.example.todo_application.databinding.TaskCardBinding

class ToDoFragmentRecyclerViewAdapter(private var items: List<Task>) :
    RecyclerView.Adapter<ToDoFragmentRecyclerViewAdapter.ViewHolder>() {
    private lateinit var binding: TaskCardBinding
    private val TAG = "ToDoFragmentRecyclerViewAdapter"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = TaskCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = items[position]
        holder.bind(item, position)

        holder.itemBinding.RightMarkCard.setOnClickListener {
            Log.d(TAG, "Right Mark Clicked")
            items[position].isCompleted = true
            MyDataBase.getInstance().tasksDao().updateTask(items[position])
            notifyItemChanged(position)
        }
    }

     fun setNewTaskList(newTaskList: List<Task>) {
        this.items = newTaskList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val itemBinding: TaskCardBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Task, position: Int) {
            itemBinding.TaskTitleTv.text = item.title
            itemBinding.TaskTimeTv.text = item.timeStamp.toString()
            if (item.isCompleted == true) {
                itemBinding.RightMarkCard.visibility = View.INVISIBLE
                itemBinding.DoneTextTv.visibility = View.VISIBLE
            }
            else{
                itemBinding.RightMarkCard.visibility = View.VISIBLE
                itemBinding.DoneTextTv.visibility = View.INVISIBLE
            }
        }
    }
}