package com.example.japancars.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.japancars.data.repository.ExchangeRateRepository
import com.example.japancars.model.ExchangeRate
import com.example.japancars.model.ExchangeRateItem
import kotlinx.coroutines.launch
import retrofit2.Response


class ExchangeRateViewModel(application: Application): AndroidViewModel(application) {

    var repo = ExchangeRateRepository()
    val myRateList: MutableLiveData<Response<ExchangeRate>> = MutableLiveData()

    fun getActualRate(){
        viewModelScope.launch {
            myRateList.value = repo.getActualRate()
        }
    }

}