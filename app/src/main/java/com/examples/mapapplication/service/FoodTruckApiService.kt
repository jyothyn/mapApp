package com.examples.mapapplication.service

import com.examples.mapapplication.data.TruckSchedule
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodTruckApiService {
    //https://data.sfgov.org/resource/jjew-r69b.json/?dayorder=5
    @GET(".")
    suspend fun getScheduleOfDay(@Query("dayorder") day: Int): List<TruckSchedule>

//    suspend fun someApiCall(day:Int) : LiveData<TruckSchedule> {
//        return liveData<TruckSchedule> {
//            val someData = getScheduleOfDay(day)
//            emit(someData)
//        }
//    }
}
