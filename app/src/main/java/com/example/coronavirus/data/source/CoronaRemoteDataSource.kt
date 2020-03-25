package com.example.coronavirus.data.source

import com.example.coronavirus.api.BaseDataSource
import com.example.coronavirus.api.CoronaService
import javax.inject.Inject


class CoronaRemoteDataSource @Inject constructor(private val service: CoronaService) :
    BaseDataSource() {

    suspend fun fetchCoronaCountrySets() =
        getResult { service.getCountriesCoronaList() }

    suspend fun fetchCoronaCountryListSoretd(sortBy: String) =
        getResult { service.getCountriesCoronaListSorted(sortBy) }

    suspend fun fetchCoronaAllDetails() =
        getResult { service.getAllCoronaDetails() }

    suspend fun fetchCoronaByCountry(country: String) =
        getResult { service.getCountryCorona(country) }
}
