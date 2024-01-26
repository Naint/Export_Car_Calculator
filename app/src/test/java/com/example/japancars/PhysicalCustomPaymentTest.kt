package com.example.japancars

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PhysicalCustomPaymentTest {

    private val pcm = PhisycalCustomPayment()

    @Test
    fun whenAdding1500And93_thenAnswer237150() {
        assertEquals(237150, pcm.calculatePaymentThreeToFive(1500, 93.0).toInt())
    }
}