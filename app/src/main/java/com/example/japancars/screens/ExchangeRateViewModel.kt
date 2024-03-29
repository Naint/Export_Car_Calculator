package com.example.japancars.screens

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup


class ExchangeRateViewModel(application: Application): AndroidViewModel(application) {

    private var yenRate: Double = 0.0
    private var euroRate: Double = 0.0

    var yenRateSwift: Double = 0.0
    var dollarRateSwift: Double = 0.0

    var euroRateCb: Double = 0.0
    var dollarRateCb: Double = 0.0
    var yenRateCb: Double = 0.0
    var vonRateCb: Double = 0.0

    fun getRateInfo(){
        try{
            viewModelScope.launch(Dispatchers.IO) {
                val doc = Jsoup.connect("https://www.atb.su/services/transfer/transfer-bank/").get()
                val rateTable = doc.getElementById("currencyTab1").text().toString().split(" ")
                Log.i("euro", rateTable[9])
                Log.i("yen", rateTable[18])
                Log.i("dollar", rateTable[6])
                dollarRateSwift = rateTable[6].toDouble()
                yenRateSwift = rateTable[18].toDouble()
                setEuroRate(rateTable[9].toDouble())
                setYenRate(rateTable[18].toDouble())
            }
        }
        catch (_ : Exception){
        }
    }

    fun getRatesCb(){
        try{
            viewModelScope.launch(Dispatchers.IO){
                val doc = Jsoup.connect("https://cbr.ru/currency_base/daily/").get()
                val rateTable = doc.getElementsByTag("table")[0]
                dollarRateCb = rateTable
                    .getElementsByTag("tr")[14]
                    .getElementsByTag("td")[4].text().replace(",", ".").toDouble()
                euroRateCb = rateTable
                    .getElementsByTag("tr")[15]
                    .getElementsByTag("td")[4].text().replace(",", ".").toDouble()
                yenRateCb = rateTable
                    .getElementsByTag("tr")[43]
                    .getElementsByTag("td")[4].text().replace(",", ".").toDouble()
                vonRateCb = rateTable
                    .getElementsByTag("tr")[8]
                    .getElementsByTag("td")[4].text().replace(",", ".").toDouble() / 1000
            }


        }catch (_ : Exception){

        }
        Log.i("rates", "$dollarRateCb $euroRateCb $yenRateCb $vonRateCb")
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