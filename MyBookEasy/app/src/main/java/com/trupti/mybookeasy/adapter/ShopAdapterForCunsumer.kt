package com.trupti.mybookeasy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.entity.Shop

class ShopAdapterForConsumer(private val shopList: List<Shop>, private val context: Context) :
    RecyclerView.Adapter<ShopAdapterForConsumer.ShopViewHolder>() {  // ✅ Fixed class reference

    class ShopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shopName: TextView = view.findViewById(R.id.shopName)
        val shopDescription: TextView = view.findViewById(R.id.shopDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop, parent, false)
        return ShopViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val shop = shopList[position]
        holder.shopName.text = shop.name
        holder.shopDescription.text = shop.description
    }

    override fun getItemCount(): Int {
        return shopList.size
    }
}
