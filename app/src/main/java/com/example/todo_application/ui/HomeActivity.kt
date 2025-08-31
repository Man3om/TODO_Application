package com.example.todo_application.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todo_application.R
import com.example.todo_application.databinding.ActivityHomeBinding
import com.example.todo_application.fragments.AddingBottomSheetFragment
import com.example.todo_application.fragments.ListFragment
import com.example.todo_application.fragments.SettingsFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var bottomSheetFragment: AddingBottomSheetFragment

    private lateinit var listFragment: ListFragment
    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "HomeActivity Started")
        initFabButton()
        initNavBar()
    }
    private fun initNavBar() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_tasks_list -> {
                    Log.i(TAG, "menu_tasks_list selected")
                    navigateFragment(listFragment)
                }

                R.id.menu_settings -> {
                    Log.i(TAG, "menu_settings selected")
                    navigateFragment(SettingsFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavigationView.selectedItemId = R.id.menu_tasks_list
    }

    private fun initFabButton()
    {
        listFragment = ListFragment()
        binding.todoFab.setOnClickListener {
            Log.d(TAG, "Fab Button Clicked")
            bottomSheetFragment = AddingBottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, null)

            bottomSheetFragment.onTaskAddedListener = {
                listFragment.getTasksByDate()
            }
        }
    }

    private fun navigateFragment(fragment: Fragment)
    {
        Log.d(TAG, "Navigating to $fragment")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}