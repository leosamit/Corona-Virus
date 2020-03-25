package com.example.coronavirus.api

import com.google.gson.annotations.SerializedName

data class ResultsResponse<T>(
    val coronaStats: List<T>
)
//    @SerializedName("error")
//    val error: Boolean?,
//    @SerializedName("statusCode")
//    val next: Int?,
//    @SerializedName("message")
//    val message: String?,
//    @SerializedName("data")
//    val covidaData: CovidData<T>


//data class CovidData<T>(
//    @SerializedName("lastChecked")
//    val lastChecked: String?,
//    @SerializedName("covid19Stats")
//    val coronaStats: List<T>
//)