package com.trupti.mybookeasy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trupti.mybookeasy.entity.Booking
import com.trupti.mybookeasy.R

class BookingAdapter(private val bookings: List<Booking>) :
    RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_booking, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = bookings[position]
        holder.bind(booking)
    }

    override fun getItemCount(): Int {
        return  bookings.size
    }

    class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(booking: Booking) {
            itemView.findViewById<TextView>(R.id.bookingDate).text = "Date: ${booking.date}"
            itemView.findViewById<TextView>(R.id.bookingTime).text = "Time: ${booking.time}"
            val servicesText = booking.services.joinToString(", ") { it.name }
            itemView.findViewById<TextView>(R.id.bookingServices).text = "Services: $servicesText"
        }
    }
}