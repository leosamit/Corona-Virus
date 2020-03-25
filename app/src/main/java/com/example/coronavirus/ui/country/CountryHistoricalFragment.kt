package com.example.coronavirus.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.coronavirus.R
import com.example.coronavirus.ui.CoronaStatsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CountryHistoricalFragment:DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CoronaStatsViewModel
    //private lateinit var binding: FragmentCou


//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding =
//            DataBindingUtil.inflate(inflater, R.layout.fragment_country_historical, container, false)
//        return binding.root
//    }

}