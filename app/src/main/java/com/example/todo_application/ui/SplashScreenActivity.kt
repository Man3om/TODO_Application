package com.example.todo_application.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.todo_application.R

class SplashScreenActivity : AppCompatActivity() {
    private val TAG = "SplashScreenActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash_screen)
        Log.d(TAG, "SplashScreenActivity Started")

        Handler(Looper.getMainLooper()).postDelayed(
                {
                    Log.d(TAG, "Navigating to HomeActivity")
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }, 2500
        )

    }
}