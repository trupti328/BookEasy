package com.trupti.mybookeasy.entity

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category_id") val id: Int,  // Ensure correct JSON key mapping
    val name: String,
    val imageResId: Int = 0  // Default to 0, will be set later
)
