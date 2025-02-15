package com.trupti.mybookeasy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.entity.Service


class ServiceAdapter(private val serviceList: List<Service>) :
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceName: TextView = itemView.findViewById(R.id.serviceName)
        val serviceDuration: TextView = itemView.findViewById(R.id.serviceDuration)
        val servicePrice: TextView = itemView.findViewById(R.id.servicePrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = serviceList[position]
        holder.serviceName.text = service.name
        holder.serviceDuration.text = service.duration
        holder.servicePrice.text = service.price
    }

    override fun getItemCount(): Int = serviceList.size
}
