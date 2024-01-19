package com.example.japancars.data.repository

import com.example.japancars.data.api.RetrofitInstance
import com.example.japancars.model.ExchangeRate
import retrofit2.Response


class ExchangeRateRepository {

    suspend fun getActualRate(): Response<ExchangeRate> {
        return RetrofitInstance.api.getActualRate()
    }

}