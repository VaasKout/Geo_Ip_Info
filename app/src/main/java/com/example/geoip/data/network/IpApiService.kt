package com.example.geoip.data.network

import retrofit2.http.GET

interface IpApiService {
    @GET("./")
    suspend fun getInfo(): InfoJson
}