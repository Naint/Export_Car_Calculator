package com.example.japancars.screens.japan

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.japancars.PhisycalCustomPayment

class JapanCalcViewModel(application: Application): AndroidViewModel(application) {

    private var customPayment = 0.0
    var carPriceYenLiveData = MutableLiveData<Int>()
    var customPaymentLiveData = MutableLiveData<Double>()

    private val japanExpense = 100000
    private val averageComission = 50000
    private val freight = 450
    private val expenseInRussia = 80000


    fun init(customPayment: Double){
        customPaymentLiveData.value = customPayment
    }

    fun setCarPrice(carPrice: Int){
        carPriceYenLiveData.value = carPrice
    }

    fun calculateFinalPrice(yenRate: Double) : Double{
        val buffYenRate = yenRate / 100
        return getRublesPrice(carPriceYenLiveData.value!!.toInt(), buffYenRate) + japanExpense * buffYenRate + freight * 90 + averageComission + customPaymentLiveData.value!!.toDouble() + expenseInRussia
    }

    fun getCustomPrice(age: Int, capacity: Int, carPrice: Int, yenRate: Double, euroRate: Double): Double{
        var price = 0.0
        var n = PhisycalCustomPayment()

        if(age in 0..2){
            price = n.calculatePaymentLessThree(carPrice, yenRate, euroRate)
            customPayment = price
        }
        else if (age in 3..5){
            price = n.calculatePaymentThreeToFive(capacity, euroRate)
            customPayment = price
        }
        else if(age > 5){
            price = n.calculatePaymentMoreFive(capacity, euroRate)
            customPayment = price
        }

        return price
    }

    private fun getRublesPrice(jpy: Int, rate : Double): Double{
        return jpy * rate
    }

}