package com.example.coronavirus.data.source

import com.example.coronavirus.api.BaseDataSource
import com.example.coronavirus.api.CoronaService
import javax.inject.Inject


class CoronaRemoteDataSource @Inject constructor(private val service: CoronaService) :
    BaseDataSource() {

    suspend fun fetchCoronaSets() =
        getResult { service.getAllCoronaList() }
}
