package com.example.coronavirus.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.coronavirus.util.DateConverter
import com.example.coronavirus.util.convertDate
import com.google.gson.annotations.SerializedName

data class CoronaCountryStats @RequiresApi(Build.VERSION_CODES.N) constructor(
    @SerializedName("country")
    val country: String?,
    @SerializedName("cases")
    val cases: Int?,
    @SerializedName("todayCases")
    val todayCases: Int?,
    @SerializedName("todayDeaths")
    val todayDeaths: Int?,
    @SerializedName("deaths")
    val deaths: Int?,
    @SerializedName("recovered")
    val recovered: Int?,
    @SerializedName("active")
    val active: Int?,
    @SerializedName("critical")
    val critical: Int?,
    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Int,
    @SerializedName("updated")
    val updated: Long,
    @SerializedName("countryInfo")
    val countryInfo: CountryInfo,

    val lastUpdated: String? = updated.toString().convertDate()
)

data class CountryInfo(
    @SerializedName("flag")
    val flag: String?
)