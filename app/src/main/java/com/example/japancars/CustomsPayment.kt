package com.example.japancars

abstract class CustomsPayment {

    abstract fun calculatePaymentLessThree(carPrice : Int, yenRate: Double, euroRate: Double): Double

    abstract fun calculatePaymentThreeToFive(capacity: Int, euroRate: Double): Double

    abstract fun calculatePaymentMoreFive(capacity: Int, euroRate: Double): Double

}