package com.example.japancars

abstract class CustomsPayment {

    abstract fun calculatePaymentLessThree(capacity: Int): Double

    abstract fun calculatePaymentThreeToFive(capacity: Int): Double

    abstract fun calculatePaymentMoreFive(capacity: Int): Double

}