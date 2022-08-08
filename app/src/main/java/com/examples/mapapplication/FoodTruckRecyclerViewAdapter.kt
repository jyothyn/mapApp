package com.examples.mapapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.examples.mapapplication.data.TruckSchedule


class FoodTruckRecyclerViewAdapter(
    private val scheduleList: List<TruckSchedule>
) : RecyclerView.Adapter<FoodTruckRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_food_truck_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = scheduleList[position]
        holder.bindVal(item)
    }

    override fun getItemCount(): Int = scheduleList.size

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val truckName: TextView = view.findViewById(R.id.truck_name)
        val truckAddr: TextView = view.findViewById(R.id.truck_address)
        val truckDesc: TextView = view.findViewById(R.id.truck_desc)
        val truckTime: TextView = view.findViewById(R.id.truck_time)

        fun bindVal(item: TruckSchedule) {
            truckName.text = item.applicant
            truckAddr.text = item.location
            truckDesc.text = item.locationdesc
            truckTime.text = "${item.starttime} - ${item.endtime}"
        }
    }
}