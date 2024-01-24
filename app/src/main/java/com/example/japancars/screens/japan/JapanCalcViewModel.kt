package com.example.japancars.screens.japan

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.japancars.PhisycalCustomPayment

class JapanCalcViewModel(application: Application): AndroidViewModel(application) {

    private var carPriceYen = 2257000
    private var customPayment = 0.0
    var carPriceYenLiveData = MutableLiveData<Int>()
    var customPaymentLiveData = MutableLiveData<Double>()



    private var japanExpense = 100000

    private var averageComission = 50000
    private var freight = 450


    fun init(customPayment: Double, ){

        customPaymentLiveData.value = customPayment
    }

    fun setCarPrice(carPrice: Int){
        carPriceYenLiveData.value = carPrice
    }

    fun calculateFinalPrice(yenRate: Double) : Double{
        //Log.i("LOGINFO", "${getRublesPrice(carPriceYenLiveData.value!!.toInt(), 61.0)} \n ${customPaymentLiveData.value}" )
        return getRublesPrice(carPriceYenLiveData.value!!.toInt(), 0.63) + japanExpense + freight * 90 + averageComission + customPaymentLiveData.value!!.toDouble()
    }


    //2598000
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


    private fun getRublesPrice(jpy: Int, rate : Double): Double{
        return jpy * rate
    }

}