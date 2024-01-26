package com.example.japancars.screens.Korea

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.japancars.PhisycalCustomPayment

class KoreaCalcViewModel(application: Application): AndroidViewModel(application) {


    var carPriceVonLiveData = MutableLiveData<Int>()
    var customPaymentLiveData = MutableLiveData<Double>()

    private var customPayment = 0.0
    private var koreaExpense = 200000
    private var averageComission = 50000
    private var freight = 80000
    private var expenseInRussia = 100000

    fun setCustomPayment(customPayment: Double){
        customPaymentLiveData.value = customPayment
    }

    fun setCarPrice(carPrice: Int){
        carPriceVonLiveData.value = carPrice
    }



    private fun getRublesPrice(von: Int, rate : Double): Double{
        return von * rate
    }

    fun calculateFinalPrice(vonRate: Double) : Double{
        return getRublesPrice(carPriceVonLiveData.value!!.toInt(), vonRate) + koreaExpense + freight + averageComission + customPaymentLiveData.value!!.toDouble() + expenseInRussia
    }

    fun getCustomPrice(age: Int, capacity: Int, carPrice: Int, yenRate: Double, euroRate: Double): Double{
        var price = 0.0
        var n = PhisycalCustomPayment()

        if(age in 0..2){

            price = n.calculatePaymentLessThree(carPrice, yenRate, euroRate)
            Log.i("carPriceLiveData", price.toString())
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
}