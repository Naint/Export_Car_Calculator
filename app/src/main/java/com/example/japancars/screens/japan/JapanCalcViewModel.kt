package com.example.japancars.screens.japan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.japancars.CustomsPayment
import com.example.japancars.PhisycalCustomPayment

class JapanCalcViewModel(application: Application): AndroidViewModel(application) {

    private var carPriceYen = 2257000
    private var japanExpense = 100000
    private var averageComission = 50000
    private var freight = 450

    fun calculateFinalPrice() : Double{
        return getRublesPrice(2257000, 63.7) + japanExpense + freight * 90
    }

    private fun getCustomPrice(age: Int, capacity: Int): Double{
        var price = 0.0
        var n: PhisycalCustomPayment = PhisycalCustomPayment()

        if(age in 0..2){
            price = n.calculatePaymentLessThree(capacity)
        }
        else if (age in 3..5){
            price = n.calculatePaymentThreeToFive(capacity)
        }
        else if(age > 5){
            price = n.calculatePaymentMoreFive(capacity)
        }
        
        return price

    }


    private fun getRublesPrice(jpy: Int, rate : Double): Double{
        return jpy / rate
    }

}