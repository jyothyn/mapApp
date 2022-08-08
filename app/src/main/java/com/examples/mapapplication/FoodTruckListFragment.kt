package com.examples.mapapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.examples.mapapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodTruckListFragment : Fragment() {

    private val mvm: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_truck_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                mvm.scheduleList.observe(viewLifecycleOwner) { schList ->
                    adapter = FoodTruckRecyclerViewAdapter(schList)
                }
            }
        }
        return view
    }
}