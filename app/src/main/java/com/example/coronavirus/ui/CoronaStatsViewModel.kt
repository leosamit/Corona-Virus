package com.example.coronavirus.ui

import androidx.lifecycle.ViewModel
import com.example.coronavirus.data.repository.CoronaRepository
import javax.inject.Inject

class CoronaStatsViewModel @Inject constructor(private val repository: CoronaRepository) :
    ViewModel() {


    fun getCountryCoronaList(sortBy: String) {
        //repository.getCountryCoronaList()
        repository.getCountryCoronaListSoretd(sortBy)
    }

    fun getAllCoronaDetails() {
        repository.getAllCoronaDetails()
    }

    fun getCoronaByCountry(country: String) {
        repository.getCoronaByCountry(country)
    }


    val coronaCountryList = repository.coronaCountryStats
    val allCoronaDetails = repository.allCoronaDetails
    val coronaByCountry = repository.coronaByCountry

}
