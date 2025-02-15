package com.trupti.mybookeasy.entity

data class TimeSlot(val time: String,
                    val isAvailable: Boolean,
                    var isSelected: Boolean = false )
