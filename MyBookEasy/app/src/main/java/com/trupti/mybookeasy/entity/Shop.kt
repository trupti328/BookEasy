package com.trupti.mybookeasy.entity

import android.os.Parcel
import android.os.Parcelable

data class Shop(
    val shop_id: Int,
    val owner_id: Int,
    val name: String,
    val description: String,
    val contact_number: String,
    val category_id: Int,
    val created_at: String,
    val status: String
)