package com.example.japancars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.japancars.databinding.ActivityMainBinding
import com.example.japancars.screens.Korea.KoreaCalculatorFragment
import com.example.japancars.screens.japan.JapanCalculatorFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(JapanCalculatorFragment())

        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.calculatorNavElement -> replaceFragment(JapanCalculatorFragment())
                R.id.historyNavElement -> replaceFragment(KoreaCalculatorFragment())
                else ->{

                }
            }

            true
        }

    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_layout, fragment)
        fragmentTransaction.commit()
    }

}