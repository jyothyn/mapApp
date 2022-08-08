package com.examples.mapapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.mapapplication.data.TruckSchedule
import com.examples.mapapplication.service.FoodTruckRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val foodTruckRepo: FoodTruckRepository
) : ViewModel() {
//    @Inject
//    lateinit var foodTruckRepo: FoodTruckRepository

    private val _scheduleList = MutableLiveData<List<TruckSchedule>>()
    val scheduleList: MutableLiveData<List<TruckSchedule>> = _scheduleList
    var showListFrag = true

    private var exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            println("Error Received -> " + throwable.message)
        }

    init {
        getFoodSchedule()
    }

    private fun getFoodSchedule() {
        val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val hr = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        viewModelScope.launch(exceptionHandler) {

            val todaySchedule = foodTruckRepo.getTruckSchedule(day - 1)
            val fil = todaySchedule
                .filter { hr in it.start24.hour() until it.end24.hour() }
                .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.applicant })
            _scheduleList.value = fil
        }
    }

    private fun String.hour(): Int = substring(0, 2).toInt()
}