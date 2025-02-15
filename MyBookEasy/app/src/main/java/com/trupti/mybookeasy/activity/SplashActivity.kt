package com.trupti.mybookeasy.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.trupti.mybookeasy.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val tvCompanyName = findViewById<TextView>(R.id.tvCompanyName)

        val fadeIn = AlphaAnimation(0f,1f)
        fadeIn.duration=2000
        tvCompanyName.startAnimation(fadeIn)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
            finish()
        },3000)
    }
}