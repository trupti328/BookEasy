package com.trupti.mybookeasy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trupti.mybookeasy.entity.Category
import com.trupti.mybookeasy.R

class CategoryAdapter(
    private val categories: List<Category>,
    private val context: Context,
    private val clickListener: CategoryClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
        holder.itemView.setOnClickListener {
            clickListener.onCategoryClick(category)  // ✅ Correct way to handle click
        }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryName: TextView = itemView.findViewById(R.id.categoryName)
        private val categoryImage: ImageView = itemView.findViewById(R.id.categoryImage)

        fun bind(category: Category) {
            categoryName.text = category.name
            categoryImage.setImageResource(category.imageResId)
        }
    }

    interface CategoryClickListener {
        fun onCategoryClick(category: Category)  // Define click listener interface
    }
}
