package com.example.coronavirus.ui.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.crashlytics.android.Crashlytics
import com.example.coronavirus.R
import com.example.coronavirus.data.Result
import com.example.coronavirus.databinding.FragmentWorldDetailBinding
import com.example.coronavirus.di.injectViewModel
import com.example.coronavirus.ui.CoronaStatsViewModel
import com.example.coronavirus.util.convertDate
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_corona_stats.*
import timber.log.Timber
import javax.inject.Inject


class WorldDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CoronaStatsViewModel
    private lateinit var binding: FragmentWorldDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_world_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    fun initUI() {
        //Crashlytics.getInstance().crash()
        //binding2.parameterText=binding.containerDetail.parameterText
        viewModel = injectViewModel(viewModelFactory)

        srl_corona_full_list.setOnRefreshListener {
            viewModel.getAllCoronaDetails()
        }
        viewModel.getAllCoronaDetails()

        viewModel.allCoronaDetails.observe(this@WorldDetailFragment, Observer { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    Timber.d("Loading")
                    binding.isLoading = true
                }
                Result.Status.SUCCESS -> {
                    binding.isLoading = false
                    binding.coronaStats = result.data!!
                    binding.lastUpdated = result.data.updated.toString().convertDate()
                    Timber.d("Success Date ${result.data.updated.toString().convertDate()}")

                }

                Result.Status.ERROR -> {
                    binding.isLoading = false
                    Snackbar.make(binding.root, result.message.toString()!!, Snackbar.LENGTH_LONG)
                        .show()
                    Timber.d(result.message.toString()!!)
                }


            }

        })
    }
}
