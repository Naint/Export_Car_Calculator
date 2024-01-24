package com.example.japancars

import android.util.Log

class PhisycalCustomPayment : CustomsPayment()
{

    override fun calculatePaymentLessThree(carPrice: Int, yenRate: Double, euroRate: Double): Double {

        var yenRateBuff = yenRate / 100
        val carEuroPrice = carPrice * yenRateBuff / euroRate
        var percent = 0.0
        Log.i("infoRate", "${yenRateBuff} ${carPrice} ${euroRate}")

        if(carEuroPrice < 8500){
            percent = 0.54
        }
        else if(carEuroPrice >= 8500 && carEuroPrice < 16700){
            percent = 0.48
        }
        else if(carEuroPrice >= 16700 && carEuroPrice < 42300){
            percent = 0.48
        }
        else if(carEuroPrice >= 42300 && carEuroPrice < 84500){
            percent = 0.48
        }

        return carPrice * yenRateBuff * percent
    }

    override fun calculatePaymentThreeToFive(capacity: Int, euroRate: Double): Double{

        var percent: Double = 1.0

        if(capacity in 0..1000){
            percent = 1.5
        }
        else if(capacity in 1001..1500){
            percent = 1.7
        }
        else if(capacity in 1501..1800){
            percent = 2.5
        }
        else if(capacity in 1801..2300){
            percent = 2.7
        }
        else if(capacity in 2301..3000){
            percent = 3.0
        }
        else if(capacity > 3001){
            percent = 3.6
        }

        return capacity * percent * euroRate

    }

    override fun calculatePaymentMoreFive(capacity: Int, euroRate: Double): Double {
        var percent: Double = 1.0

        if(capacity in 0..1000){
            percent = 3.0
        }
        else if(capacity in 1001..1500){
            percent = 3.2
        }
        else if(capacity in 1501..1800){
            percent = 3.5
        }
        else if(capacity in 1801..2300){
            percent = 4.8
        }
        else if(capacity in 2301..3000){
            percent = 5.0
        }
        else if(capacity > 3001){
            percent = 5.7
        }

        return capacity * percent * euroRate
    }


}