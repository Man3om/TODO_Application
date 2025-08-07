package com.example.todo_application.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todo_application.R
import com.example.todo_application.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavBar()
        initFabButton()

    }
    private fun initNavBar()
    {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_tasks_list -> {
                    TODO("Not yet implemented")
                }

                R.id.menu_settings -> {
                    TODO("Not yet implemented")
                }

                else -> false
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.menu_tasks_list
    }

    private fun initFabButton()
    {
        binding.todoFab.setOnClickListener {
            TODO("Not yet implemented")
        }
    }

    private fun navigateFragment(fragment: Fragment)
    {
        TODO("Not yet implemented")
    }
}