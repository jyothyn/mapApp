package com.examples.mapapplication.service

import com.examples.mapapplication.model.TruckSchedule

class FoodTruckRepositoryImpl(
    private val foodTruckApi: FoodTruckApiService
) : FoodTruckRepository {
    override suspend fun getTruckSchedule(day: Int): List<TruckSchedule> {
        return foodTruckApi.getScheduleOfDay(day)
    }
}