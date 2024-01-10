package com.example.japancars

class PhisycalCustomPayment : CustomsPayment()
{
    private var euroRate : Double = 97.87

    override fun calculatePaymentLessThree(capacity: Int): Double {
        return 0.0
    }

    override fun calculatePaymentThreeToFive(capacity: Int): Double{

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

    override fun calculatePaymentMoreFive(capacity: Int): Double {
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