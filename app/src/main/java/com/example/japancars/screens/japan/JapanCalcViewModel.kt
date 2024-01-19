package com.example.japancars.screens.japan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.japancars.PhisycalCustomPayment

class JapanCalcViewModel(application: Application): AndroidViewModel(application) {

    //private var carPriceYen = 2257000
    var carPriceYenLiveData = MutableLiveData<Int>()
    var customPaymentLiveData = MutableLiveData<Double>()



    private var japanExpense = 100000
    private var customPayment = 0.0
    private var averageComission = 50000
    private var freight = 450


    fun init(carPrice: Int, customPayment: Double, ){
        carPriceYenLiveData.value = carPrice
        customPaymentLiveData.value = customPayment
    }

    fun calculateFinalPrice() : Double{
        return getRublesPrice(1000000, 61.0) + japanExpense + freight * 90 + averageComission + customPayment
    }



    fun getCustomPrice(age: Int, capacity: Int): Double{
        var price = 0.0
        var n = PhisycalCustomPayment()

        if(age in 0..2){
            price = n.calculatePaymentLessThree(capacity)
            customPayment = price
        }
        else if (age in 3..5){
            price = n.calculatePaymentThreeToFive(capacity)
            customPayment = price
        }
        else if(age > 5){
            price = n.calculatePaymentMoreFive(capacity)
            customPayment = price
        }

        return price

    }


    private fun getRublesPrice(jpy: Int, rate : Double): Double{
        return jpy / rate
    }

}