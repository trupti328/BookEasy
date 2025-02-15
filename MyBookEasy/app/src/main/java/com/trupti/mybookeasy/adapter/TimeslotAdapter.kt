package com.trupti.mybookeasy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.entity.TimeSlot

class TimeSlotAdapter(private val timeSlots: List<TimeSlot>, private val onItemClick: (TimeSlot) -> Unit) : RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_time_slot, parent, false)
        return TimeSlotViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        val timeSlot = timeSlots[position]
        holder.bind(timeSlot)
    }

    override fun getItemCount(): Int = timeSlots.size

    inner class TimeSlotViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val timeSlotText: TextView = itemView.findViewById(R.id.timeSlotText)
        private val slotAvailability: TextView = itemView.findViewById(R.id.slotAvailability)
        private val selectSlot: CheckBox = itemView.findViewById(R.id.selectSlot)

        fun bind(timeSlot: TimeSlot) {
            timeSlotText.text = timeSlot.time
            slotAvailability.text = if (timeSlot.isAvailable) "Available" else "Booked"
            selectSlot.isChecked = timeSlot.isSelected

            // Set click listener for the item
            itemView.setOnClickListener {
                onItemClick(timeSlot)
            }
        }
    }
}
