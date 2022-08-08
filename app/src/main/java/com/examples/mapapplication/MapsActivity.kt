package com.examples.mapapplication

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.examples.mapapplication.databinding.ActivityMapsBinding
import com.examples.mapapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapsBinding
//    private lateinit var viewModel: MainViewModel
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("..onCreate")
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.lifecycleOwner = this
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        showFragment(FoodTruckListFragment())
        binding.progressIndicator.visibility = View.GONE

        binding.menuButton.let { link ->
            link.setOnClickListener {
                viewModel.showListFrag = !viewModel.showListFrag
                link.text =
                    if (viewModel.showListFrag) getString(R.string.map) else getString(R.string.list)
                if (viewModel.showListFrag) showFragment(FoodTruckListFragment())
                else showFragment(MapsFragment())
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }
}