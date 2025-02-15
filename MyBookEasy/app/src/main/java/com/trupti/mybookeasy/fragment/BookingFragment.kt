package com.trupti.mybookeasy.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trupti.mybookeasy.BookingManager
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.adapter.BookingAdapter


class BookingFragment : Fragment() {

    private lateinit var bookingsRecyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookingsRecyclerView = view.findViewById(R.id.bookingsRecyclerView)
        bookingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadBookings()
    }

    private fun loadBookings() {
        val booking = BookingManager.getBookings()
        val adapter = BookingAdapter(booking)
        bookingsRecyclerView.adapter = adapter
    }

}