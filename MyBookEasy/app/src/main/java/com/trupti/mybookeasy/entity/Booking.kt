package com.trupti.mybookeasy.entity


data class Booking(
    val services: List<Service>,
    val date: String,
    val time: String
)
