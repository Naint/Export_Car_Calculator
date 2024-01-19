package com.example.japancars.data.api

import com.example.japancars.model.ExchangeRate
import com.example.japancars.model.ExchangeRateItem
import retrofit2.http.GET
import retrofit2.Response

interface ApiService {

    @GET("URL api")
    suspend fun getActualRate(): Response<ExchangeRate>
}