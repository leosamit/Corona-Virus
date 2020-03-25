package com.example.coronavirus.api

import com.example.coronavirus.data.CoronaCountryStats
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoronaService {

    @GET("countries")
    suspend fun getCountriesCoronaList(): Response<List<CoronaCountryStats>>

    @GET("all")
    suspend fun getAllCoronaDetails(): Response<CoronaCountryStats>

    @GET("countries/{country}")
    suspend fun getCountryCorona(@Path("country") country: String): Response<CoronaCountryStats>

//    @GET("countries?sort={parameter}")
//    suspend fun getCountriesCoronaListSorted(@Path("parameter") parameter: String): Response<List<CoronaCountryStats>>

    @GET("countries")
    suspend fun getCountriesCoronaListSorted(@Query("sort") parameter: String): Response<List<CoronaCountryStats>>


}