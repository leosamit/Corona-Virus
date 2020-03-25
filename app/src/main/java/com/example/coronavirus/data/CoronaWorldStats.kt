package com.example.coronavirus.data

import com.google.gson.annotations.SerializedName

data class CoronaWorldStats(
    @SerializedName("province")
    val province: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("lastUpdate")
    val lastUpdate: String?,
    @SerializedName("confirmed")
    val confirmed: Int?,
    @SerializedName("deaths")
    val deaths: Int?,
    @SerializedName("recovered")
    val recovered: Int?
)
