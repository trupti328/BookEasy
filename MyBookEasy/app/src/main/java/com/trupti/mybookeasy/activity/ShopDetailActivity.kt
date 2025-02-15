package com.trupti.mybookeasy.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trupti.mybookeasy.BookingManager
import com.trupti.mybookeasy.R
import com.trupti.mybookeasy.adapter.ServiceAdapter
import com.trupti.mybookeasy.adapter.TimeSlotAdapter
import com.trupti.mybookeasy.entity.Booking
import com.trupti.mybookeasy.entity.Service
import com.trupti.mybookeasy.entity.Shop
import com.trupti.mybookeasy.entity.TimeSlot
import java.util.*

class ShopDetailActivity : AppCompatActivity() {

    private lateinit var shopName: TextView
    private lateinit var shopAddress: TextView
    private lateinit var servicesRecyclerView: RecyclerView
    //private lateinit var timeSlotsRecyclerView: RecyclerView
    private lateinit var bookButton: Button
    private lateinit var servicesAdapter: ServiceAdapter
    private lateinit var timeSlotsAdapter: TimeSlotAdapter

    private var selectedDate: String = "" // Store the selected date
    private var selectedTime: String = "" // Store the selected time
    private var selectedServices = mutableListOf<Service>() // Store selected services

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        // Initialize Views
        shopName = findViewById(R.id.shopName)
        shopAddress = findViewById(R.id.shopAddress)
        servicesRecyclerView = findViewById(R.id.serviceRecyclerView)
        //timeSlotsRecyclerView = findViewById(R.id.timeSlotsRecyclerView)
        bookButton = findViewById(R.id.btnBookAppointment)

        val selectedDateEditText: EditText = findViewById(R.id.selectedDate)
        val datePickerIcon: ImageView = findViewById(R.id.datePickerIcon)

        val selectedTimeEditText: EditText = findViewById(R.id.selectedTime)
        val timePickerIcon: ImageView = findViewById(R.id.timePickerIcon)

        val calendar = Calendar.getInstance()

        // Date Picker
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                selectedDate = "$dayOfMonth/${month + 1}/$year"
                selectedDateEditText.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerIcon.setOnClickListener {
            datePickerDialog.show()
        }

        // Time Picker
        timePickerIcon.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    selectedTime = String.format(
                        "%02d:%02d %s",
                        if (hourOfDay % 12 == 0) 12 else hourOfDay % 12,
                        minute,
                        if (hourOfDay < 12) "AM" else "PM"
                    )
                    selectedTimeEditText.setText(selectedTime)
                },
                12, 0, false
            )
            timePickerDialog.show()
        }

//        // Get data from Intent
//        val shop = intent.getParcelableExtra<Shop>("shop_data")

        // Set data to the views
//        shop?.let {
//            shopName.text = it.name
//            shopAddress.text = it.address
//
//            // Dummy data for services
//            val services = listOf(
//                Service("Hair Cut", "30 min", "₹300"),
//                Service("Facial", "45 min", "₹500"),
//                Service("Manicure", "45 min", "₹500")
//
//            )

            // Dummy data for time slots
            val timeSlots = listOf(
                TimeSlot("10:00 AM", isAvailable = true),
                TimeSlot("11:00 AM", isAvailable = false),
                TimeSlot("12:00 PM", isAvailable = true),
                TimeSlot("3:00 PM", isAvailable = true),
                TimeSlot("5:00 PM", isAvailable = false)
            )

            // Initialize and set the adapter for servicesRecyclerView
//            val servicesAdapter = ServiceAdapter(services) { selected ->
//                selectedServices = selected.toMutableList()
//            }
//            servicesRecyclerView.layoutManager = LinearLayoutManager(this)
//            servicesRecyclerView.adapter = servicesAdapter
//        }
//            servicesAdapter = ServiceAdapter(services)
//            servicesRecyclerView.layoutManager = LinearLayoutManager(this)
//            servicesRecyclerView.adapter = servicesAdapter

        }

            // Initialize and set the adapter for timeSlotsRecyclerView
//            timeSlotsAdapter = TimeSlotAdapter(timeSlots) { selectedSlot ->
//                Toast.makeText(this, "Selected Time: $selectedSlot", Toast.LENGTH_SHORT).show()
//            }
//            timeSlotsRecyclerView.layoutManager = LinearLayoutManager(this)
//            timeSlotsRecyclerView.adapter = timeSlotsAdapter
    //   }

    // Confirm Booking
//        bookButton.setOnClickListener {
//            selectedDate = selectedDateEditText.text.toString()
//            selectedTime = selectedTimeEditText.text.toString()
//
//            if (selectedServices.isNotEmpty() && selectedDate.isNotEmpty() && selectedTime.isNotEmpty()) {
//                val booking = Booking(selectedServices, selectedDate, selectedTime)
//                BookingManager.addBooking(booking) // Save booking globally
//
//                // Redirect to Shop Page
//                Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_LONG).show()
//                finish() // Close activity and return to Shop Page
//            } else {
//                Toast.makeText(this, "Please select services, date, and time!", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
//}