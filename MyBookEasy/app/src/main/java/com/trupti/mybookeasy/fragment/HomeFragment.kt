package com.trupti.mybookeasy.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.activity.ShopListActivity
import com.trupti.mybookeasy.adapter.CategoryAdapter
import com.trupti.mybookeasy.entity.Category
import com.trupti.mybookeasy.entity.CategoryResponse
import com.trupti.mybookeasy.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), CategoryAdapter.CategoryClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2) // Grid with 2 columns

        fetchCategories()

        return view
    }

    private fun fetchCategories() {
        val call = RetrofitClient.instance.getCategories()

        call.enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                if (!isAdded) return

                val ctx = requireContext()

                if (response.isSuccessful) {
                    val categoryResponse = response.body()

                    Log.d("API_RESPONSE", "Raw API Response: $categoryResponse")

                    val categories = categoryResponse?.categories?.map { category ->
                        Log.d("DEBUG", "Mapping category: ${category.id} - ${category.name}")
                        Category(
                            category.id,  // Ensure this ID is correct
                            category.name,
                            getCategoryImage(category.name)
                        )
                    } ?: emptyList()

                    Log.d("DEBUG", "Final categories list: $categories")

                    categoryAdapter = CategoryAdapter(categories, ctx, this@HomeFragment)
                    recyclerView.adapter = categoryAdapter
                } else {
                    Toast.makeText(ctx, "Failed to load categories", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                if (!isAdded) return
                val ctx = requireContext()
                Toast.makeText(ctx, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onCategoryClick(category: Category) {
        Log.d("DEBUG", "Category object: $category")
        val intent = Intent(requireContext(), ShopListActivity::class.java)
        Log.d("DEBUG", "Sending category_id: ${category.id}")
        intent.putExtra("category_id", category.id)
        startActivity(intent)
    }


    private fun getCategoryImage(categoryName: String): Int {
        return when (categoryName.lowercase()) {
            "doctor" -> R.drawable.doctor
            "spa" -> R.drawable.spa
            "salon" -> R.drawable.salon

            else -> R.drawable.baseline_image_24 // Fallback image
        }
    }
}
