package com.example.japancars

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.japancars.databinding.ActivityMainBinding
import com.example.japancars.screens.Korea.KoreaCalculatorFragment
import com.example.japancars.screens.japan.JapanCalculatorFragment
import com.example.japancars.screens.other.OtherFragment
import com.example.japancars.utils.USE_INTERNET
import com.example.japancars.utils.checkPermission

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(OtherFragment())
        //replaceFragment(JapanCalculatorFragment())
        initPermission()
        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.calculatorNavElement -> replaceFragment(JapanCalculatorFragment())
                R.id.historyNavElement -> replaceFragment(KoreaCalculatorFragment())
                R.id.otherNavElement -> replaceFragment(OtherFragment())
                else ->{

                }
            }

            true
        }
    }
    fun initPermission(){
        if(checkPermission(USE_INTERNET, this, MainActivity())){
            Toast.makeText(this, "GRANTED", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(ContextCompat.checkSelfPermission(this, USE_INTERNET) == PackageManager.PERMISSION_GRANTED){
            initPermission()
        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_layout, fragment)
        fragmentTransaction.commit()
    }
}