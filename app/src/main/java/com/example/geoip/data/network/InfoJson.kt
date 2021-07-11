package com.example.geoip.data.network

import com.google.gson.annotations.SerializedName

data class InfoJson(
    @SerializedName("query") val ip: String = "",
    @SerializedName("status") val status: String = "",
    @SerializedName("message") val message: String = "",
    @SerializedName("country") val country: String = "",
    @SerializedName("countryCode") val countryCode: String = "",
    @SerializedName("region") val region: String = "",
    @SerializedName("regionName") val regionName: String = "",
    @SerializedName("city") val city: String = "",
    @SerializedName("zip") val zip: String = "",
    @SerializedName("lat") val lat: Double = 0.0,
    @SerializedName("lon") val lon: Double = 0.0,
    @SerializedName("timezone") val timeZone: String = "",
    @SerializedName("isp") val isp: String = "",
    @SerializedName("org") val org: String = "",
    @SerializedName("as") val asValue: String = ""
)
