package com.examples.mapapplication.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.examples.mapapplication.R
import com.examples.mapapplication.databinding.ActivityMapsBinding
import com.examples.mapapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsActivity : AppCompatActivity() {
    //    private lateinit var binding: ActivityMapsBinding
    //    private lateinit var viewModel: MainViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMapsBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(this.root)
            // add action bar
            setSupportActionBar(this.toolbar)
            supportActionBar?.subtitle = "Today's schedule"
            // set lifecycleOwner to be able to change display text on liveData change
            this.lifecycleOwner = this@MapsActivity
            this.vm = viewModel
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            this.progressIndicator.visibility = View.GONE
            viewModel.showList.observe(this@MapsActivity) {
                if (it) showFragment(FoodTruckListFragment())
                else showFragment(MapsFragment())
            }
        }
        // when only using viewBinding, not dataBinding.
//        showFragment(FoodTruckListFragment())
//        binding.menuButton.let { link ->
//            link.setOnClickListener {
//                it.avoidDoubleClick()
//                viewModel.isListDisplayed.let { isList ->
//                    if (isList.value == false) showFragment(FoodTruckListFragment())
//                    else showFragment(MapsFragment())
//                    viewModel.isListDisplayed.postValue(!(isList.value ?: false))
//                }
//            }
//        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }
}