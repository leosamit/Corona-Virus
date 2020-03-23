package com.example.coronavirus.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.coronavirus.R
import com.example.coronavirus.data.Result
import com.example.coronavirus.di.injectViewModel
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class CoronaStatsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CoronaStatsViewModel
//    private val viewModel: CoronaStatsViewModel by lazy {
//        ViewModelProviders.of(this, viewModelFactory).get(CoronaStatsViewModel::class.java)
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_corona_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    fun initUI() {
        viewModel = injectViewModel(viewModelFactory)

        viewModel.coronaStats.observe(this@CoronaStatsFragment, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    Timber.d("Success")
                    //Timber.d("Success ${result.data}}")

                    //Timber.d("Success ${result.data.let { it. }}")

                }
                Result.Status.LOADING -> {
                    Timber.d("Loading")
                }
                Result.Status.ERROR -> {
                    Timber.d("Error")
                }


            }

        })
    }
}