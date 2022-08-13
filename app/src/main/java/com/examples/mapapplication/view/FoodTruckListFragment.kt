package com.examples.mapapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.examples.mapapplication.databinding.FragmentFoodTruckListBinding
import com.examples.mapapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodTruckListFragment : Fragment() {

    private val mvm: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodTruckListBinding.inflate(layoutInflater)
//        val view = inflater.inflate(R.layout.fragment_food_truck_list, container, false)
        mvm.scheduleList.observe(viewLifecycleOwner) { schList ->
            binding.recyclerView.adapter = FoodTruckRecyclerViewAdapter(schList)
        }
//        return view
        return binding.root
    }
}