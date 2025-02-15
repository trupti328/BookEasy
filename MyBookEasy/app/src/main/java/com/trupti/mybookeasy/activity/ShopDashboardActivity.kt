package com.trupti.mybookeasy.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.adapter.ServiceAdapter
import com.trupti.mybookeasy.entity.Service


class ShopDashboardActivity : AppCompatActivity() {

    private lateinit var shopNameTextView: TextView
    private lateinit var btnAddService: Button
    private lateinit var btnLogout: Button
    private lateinit var recyclerViewServices: RecyclerView
    private lateinit var serviceAdapter: ServiceAdapter
    private val serviceList = mutableListOf<Service>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_dashboard)

//        shopNameTextView = findViewById(R.id.shopNameTextView)
//        btnAddService = findViewById(R.id.btnAddService)
//        btnLogout = findViewById(R.id.btnLogout)
//        recyclerViewServices = findViewById(R.id.recyclerViewServices)
//
//        // Set up RecyclerView
//        recyclerViewServices.layoutManager = LinearLayoutManager(this)
//        serviceAdapter = ServiceAdapter(serviceList)
//        recyclerViewServices.adapter = serviceAdapter
//
//        // Load Shop Details (Placeholder)
//        shopNameTextView.text = "My Salon & Spa"
//
//        // Load Services from Backend (To Be Implemented)
//        loadServices()
//
//        // Add Service Button
//        btnAddService.setOnClickListener {
//            val intent = Intent(this, AddServiceActivity::class.java)
//            startActivity(intent)
//        }
//
//        // Logout Button
//        btnLogout.setOnClickListener {
//            Toast.makeText(this, "Logged Out!", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this, OwnerLoginActivity::class.java))
//            finish()
        }
    }

//    private fun loadServices() {
//        // Dummy data for now
//        serviceList.add(Service("Hair Cut", "30 min", "₹300"))
//        serviceList.add(Service("Facial", "45 min", "₹500"))
//        serviceAdapter.notifyDataSetChanged()
//    }

