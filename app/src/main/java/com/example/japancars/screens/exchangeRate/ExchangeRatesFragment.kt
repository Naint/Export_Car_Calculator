package com.example.japancars.screens.exchangeRate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.japancars.R
import com.example.japancars.databinding.FragmentExchangeRatesBinding
import com.example.japancars.screens.ExchangeRateViewModel
import com.example.japancars.screens.other.OtherFragment


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ExchangeRatesFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentExchangeRatesBinding? = null
    private val binding get() = _binding!!

    private lateinit var exchangeRateViewModel: ExchangeRateViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exchangeRateViewModel = ViewModelProvider(this)[ExchangeRateViewModel::class.java]

        _binding = FragmentExchangeRatesBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exchangeRateViewModel.getRatesCb()
        exchangeRateViewModel.getRateInfo()

        binding.apply {
            btnUpdateRates.setOnClickListener{
                tvDollarRateCb.text = exchangeRateViewModel.dollarRateCb.toString()
                tvEuroRateCb.text = exchangeRateViewModel.euroRateCb.toString()
                tvYenRateCb.text = exchangeRateViewModel.yenRateCb.toString()
                tvVonRateCb.text = exchangeRateViewModel.vonRateCb.toString()

                tvDollarRateSwift.text = exchangeRateViewModel.dollarRateSwift.toString()
                tvYenRateSwift.text = exchangeRateViewModel.yenRateSwift.toString()
            }
            btnBack.setOnClickListener {
                replaceFragment(OtherFragment())
            }
        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_layout, fragment)
        fragmentTransaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExchangeRatesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}