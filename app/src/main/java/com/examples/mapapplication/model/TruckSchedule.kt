package com.examples.mapapplication.model

data class TruckSchedule(
    val applicant: String,
    val location: String,
    val locationdesc: String?,
//    val dayorder: Int,
    val start24: String,
    val end24: String,
    val starttime: String,
    val endtime: String,
//    val dayofweekstr: String,
    val latitude: Double,
    val longitude: Double,
)