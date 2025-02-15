package com.trupti.mybookeasy

import com.trupti.mybookeasy.entity.Booking

object BookingManager {
    private val bookings = mutableListOf<Booking>()

    fun addBooking(booking: Booking) {
        bookings.add(booking)
    }

    fun getBookings(): List<Booking> {
        return bookings
    }
}