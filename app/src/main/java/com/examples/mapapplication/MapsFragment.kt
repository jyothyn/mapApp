package com.examples.mapapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.examples.mapapplication.data.TruckSchedule
import com.examples.mapapplication.viewmodel.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private var locationList: MutableList<TruckSchedule> = mutableListOf()

    private val callback = OnMapReadyCallback { googleMap ->
        if (locationList.isNotEmpty()) {
            val builder = LatLngBounds.builder()
            for (location in locationList) {
                val latLng = LatLng(location.latitude, location.longitude)
                val title =
                    location.location + "," + location.locationdesc + "," +
                            location.starttime + "-" + location.endtime
                googleMap.addMarker(MarkerOptions().position(latLng).title(title))
                builder.include(latLng)
            }
            locationList[0].let {
                LatLng(it.latitude, it.longitude).let { firstLoc ->
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(firstLoc))
                }
            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 0))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.scheduleList.observe(viewLifecycleOwner) { schList ->
            locationList = schList.toMutableList()
        }
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}