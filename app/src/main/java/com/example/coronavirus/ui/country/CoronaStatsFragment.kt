package com.example.coronavirus.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coronavirus.R
import com.example.coronavirus.data.Result
import com.example.coronavirus.databinding.FragmentCoronaStatsBinding
import com.example.coronavirus.di.injectViewModel
import com.example.coronavirus.ui.CoronaStatsViewModel
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_corona_stats.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class CoronaStatsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CoronaStatsViewModel
    private lateinit var binding: FragmentCoronaStatsBinding

    private val adapter: CountryAdapter?
        get() = (rv_Country.adapter as? CountryAdapter)

    var sortedByApi: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_corona_stats, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        searchCountry(getListOfCountries())
    }

    private fun initUI() {

        //binding.rvCountry.visibility = View.GONE
        handleRadioGroup()


        viewModel = injectViewModel(viewModelFactory)
        binding.showCountryList = false
        binding.showIndividualCountry = false

        srl_corona_full_list.setOnRefreshListener {
            apiCallCountryList()
        }

        binding.executePendingBindings()
        apiCallCountryList()
        viewModel.coronaCountryList.observe(this@CoronaStatsFragment, Observer { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    binding.isLoading = true
                }
                Result.Status.SUCCESS -> {
                    binding.isLoading = false
                    Timber.d("Success")
                    binding.coronaList = result.data
                    binding.showIndividualCountry = false
                    binding.showStatsList = true
                    binding.showCountryList = false
                    binding.showRadioGroup = true
                    binding.executePendingBindings()
                }

                Result.Status.ERROR -> {
                    binding.isLoading = false
                    Timber.d("Error ${result.message}")
                    Snackbar.make(binding.root, result.message.toString(), Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun searchCountry(countries: List<String>) {

        binding.countryList = countries

        adapter?.let {
            it.countryClicObs.subscribe { countryName ->
                binding.showCountryList = false
                binding.showStatsList = true
                binding.showRadioGroup = true
                getCoronaByCountry(countryName)
                binding.executePendingBindings()
            }
        }

        binding.searchView.clicks().subscribe {
        }

        search_view.textChanges().skipInitialValue().subscribe {
            binding.showStatsList = false
            binding.showRadioGroup = false
            binding.showCountryList = true
            binding.showIndividualCountry = false
            binding.rvCountry.visibility = View.VISIBLE

            var filteredCountryList: List<String> =
                countries.filter { s -> s.contains(it, ignoreCase = true) }
            binding.countryList = filteredCountryList
            binding.executePendingBindings()
        }
    }

    private fun getListOfCountries(): List<String> {
        val locales: Array<Locale> = Locale.getAvailableLocales()
        val countries = ArrayList<String>()
        for (locale in locales) {
            val country: String = locale.displayCountry
            if (country.trim { it <= ' ' }.isNotEmpty() && !countries.contains(country)) {
                countries.add(country)
            }
        }
        return countries
    }

    private fun getCoronaByCountry(countryName: String) {
        viewModel.getCoronaByCountry(countryName)
        viewModel.coronaByCountry.observe(this@CoronaStatsFragment, Observer { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    binding.isLoading = true
                }
                Result.Status.SUCCESS -> {
                    binding.isLoading = false
                    Timber.d("Success")
                    binding.coronaCountryStat = result.data
                    binding.showIndividualCountry = true
                    binding.showStatsList = false
                    binding.showRadioGroup = false
                    binding.showCountryList = false
                    binding.executePendingBindings()
                }

                Result.Status.ERROR -> {
                    binding.isLoading = false
                    Timber.d("Error ${result.message}")
                    Snackbar.make(binding.root, result.message.toString(), Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        })

    }

    private fun handleRadioGroup() {
        var checkedRadioButton =
            binding.rgSort?.findViewById(binding.rgSort.checkedRadioButtonId) as? RadioButton

        var selectedString: String by Delegates.observable("cases") { property, oldValue, newValue ->
            sortedByApi = newValue
            apiCallCountryList()
        }
        Timber.d("Sorted String $selectedString")


        binding.rgSort.setOnCheckedChangeListener { radioGroup, id ->
            checkedRadioButton =
                radioGroup?.findViewById(radioGroup.checkedRadioButtonId) as? RadioButton
            checkedRadioButton?.let {
                //sortString = checkedRadioButton?.text.toString()

                if (checkedRadioButton!!.isChecked) {
                    when (checkedRadioButton?.text) {
                        resources.getString(R.string.sort_by_cases) -> selectedString = "cases"
                        resources.getString(R.string.sort_by_today_cases) -> selectedString =
                            "todayCases"
                        resources.getString(R.string.sort_by_deaths) -> selectedString = "deaths"
                        resources.getString(R.string.sort_by_recovered) -> selectedString =
                            "recovered"
                    }
                }
                Timber.d("Sorted String $selectedString")

            }
        }
    }

    private fun apiCallCountryList() {
        viewModel.getCountryCoronaList(sortedByApi)
    }

}