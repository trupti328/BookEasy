package com.trupti.mybookeasy.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trupti.mybookeasy.ProfileFragment
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.fragment.BookingFragment
import com.trupti.mybookeasy.fragment.HomeFragment
import com.trupti.mybookeasy.fragment.ServiceFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.bottom_navigation)
        val profileIcon: ImageView = findViewById(R.id.profile_icon)

        //set default fragment
        if (savedInstanceState==null){
            loadFragment(HomeFragment())
        }

        //handle Bottom navigation
        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
              R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_bookings -> loadFragment(BookingFragment())
                R.id.nav_services -> loadFragment(ServiceFragment())

            }
            true
        }

        //Handle profile
        profileIcon.setOnClickListener{
            loadFragment(ProfileFragment())
        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}