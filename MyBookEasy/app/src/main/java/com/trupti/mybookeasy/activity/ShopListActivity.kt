package com.trupti.mybookeasy.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.adapter.ShopAdapterForConsumer  // ✅ Added missing import
import com.trupti.mybookeasy.entity.Shop
import com.trupti.mybookeasy.entity.ShopResponse
import com.trupti.mybookeasy.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var shopAdapter: ShopAdapterForConsumer
    private var categoryId: Int = -1  // Default invalid ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        categoryId = intent.getIntExtra("category_id", -1)  // Get category ID from intent
        if (categoryId != -1) {
            fetchShopsByCategory(categoryId)
        } else {
            Toast.makeText(this, "Invalid Category ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchShopsByCategory(categoryId: Int) {


        val call = RetrofitClient.instance.getShopsByCategory(categoryId)

        call.enqueue(object : Callback<List<Shop>> {
            override fun onResponse(call: Call<List<Shop>>, response: Response<List<Shop>>) {



                if (response.isSuccessful) {
                    val shops = response.body() ?: emptyList()

                    shopAdapter = ShopAdapterForConsumer(shops, this@ShopListActivity)
                    recyclerView.adapter = shopAdapter
                } else {
                    Toast.makeText(this@ShopListActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    println("Response Code: ${response.code()}")
                    println("Error Body: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Shop>>, t: Throwable) {


                Toast.makeText(this@ShopListActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

}
