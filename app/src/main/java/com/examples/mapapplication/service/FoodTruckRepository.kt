package com.examples.mapapplication.service

import com.examples.mapapplication.data.TruckSchedule

interface FoodTruckRepository {
    suspend fun getTruckSchedule(day: Int): List<TruckSchedule>
}
