package com.example.japancars.screens

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup


class ExchangeRateViewModel(application: Application): AndroidViewModel(application) {

    private var yenRate: Double = 0.0
    private var euroRate: Double = 0.0

    fun getRateInfo(){
        try{
            viewModelScope.launch(Dispatchers.IO) {
                val doc = Jsoup.connect("https://www.atb.su/services/transfer/transfer-bank/").get()
                val rateTable = doc.getElementById("currencyTab1").text().toString().split(" ")
                Log.i("euro", rateTable[9])
                Log.i("yen", rateTable[18])
                setEuroRate(rateTable[9].toDouble())
                setYenRate(rateTable[18].toDouble())
            }
        }
        catch (_ : Exception){
        }
    }

    private fun setEuroRate(eR: Double){
        euroRate = eR
    }
    private fun setYenRate(yR: Double){
        yenRate = yR
    }

    fun getEuroRate(): Double{
        return euroRate
    }

    fun getYenRate(): Double{
        return yenRate
    }
}