package com.example.coronavirus.api

import com.example.coronavirus.data.CoronaStats
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoronaService {

    @GET("stats")
    suspend fun getAllCoronaList(): Response<ResultsResponse<CoronaStats>>
}