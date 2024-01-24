package com.example.japancars.screens

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.select.Elements


class ExchangeRateViewModel(application: Application): AndroidViewModel(application) {

    private var yenRate: Double = 0.0
    private var euroRate: Double = 0.0

    fun getWeb(){
        viewModelScope.launch(Dispatchers.IO) {
            val doc = Jsoup.connect("https://www.atb.su/services/transfer/transfer-bank/").get()
            val sections = doc.getElementsByTag("section")
            val element_sections = sections[2]
            var div_elements = element_sections.children()
            Log.i("doc", doc.title())

            var s = doc.getElementById("currencyTab1").text().toString().split(" ")

            Log.i("section", doc.getElementById("currencyTab1").text().toString())
            Log.i("euro", s[9])
            Log.i("yen", s[18])
            setEuroRate(s[9].toDouble())
            setYenRate(s[18].toDouble())

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