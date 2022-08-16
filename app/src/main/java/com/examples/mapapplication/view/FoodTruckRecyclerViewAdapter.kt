package com.examples.mapapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.examples.mapapplication.databinding.FragmentFoodTruckItemBinding
import com.examples.mapapplication.model.TruckSchedule


class FoodTruckRecyclerViewAdapter(
    private val scheduleList: List<TruckSchedule>
) : RecyclerView.Adapter<FoodTruckRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentFoodTruckItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.fragment_food_truck_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = scheduleList[position]
        holder.bindVal(item)
    }

    override fun getItemCount(): Int = scheduleList.size

    inner class ViewHolder(binding: FragmentFoodTruckItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val truckName: TextView = binding.truckName
        private val truckAddr: TextView = binding.truckAddress
        private val truckDesc: TextView = binding.truckDesc
        private val truckTime: TextView = binding.truckTime

        fun bindVal(item: TruckSchedule) {
            truckName.text = item.applicant
            truckAddr.text = item.location
            truckDesc.text = item.locationdesc
            truckTime.text = "${item.starttime} - ${item.endtime}"
        }
    }
}