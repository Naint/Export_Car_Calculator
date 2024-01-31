package com.example.japancars.screens.japan

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.japancars.R
import com.example.japancars.databinding.FragmentJapanCalculatorBinding
import com.example.japancars.screens.ExchangeRateViewModel
import java.util.Locale
import kotlin.math.roundToInt

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class JapanCalculatorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentJapanCalculatorBinding? = null
    private val binding get() = _binding!!

    private lateinit var japanCalcViewModel: JapanCalcViewModel
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
        _binding = FragmentJapanCalculatorBinding.inflate(inflater, container, false)
        japanCalcViewModel = ViewModelProvider(this)[JapanCalcViewModel::class.java]
        exchangeRateViewModel = ViewModelProvider(this)[ExchangeRateViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exchangeRateViewModel.getRateInfo()
        binding.calculateButton.setOnClickListener{
            showPriceDialog()
        }
    }


    private fun showPriceDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.price_dialog_japan, null)
        builder.setView(customView)

        val carPriceEditText = customView.findViewById<EditText>(R.id.carPriceInfo)
        val customPriceEditText = customView.findViewById<EditText>(R.id.customPriceED)
        val totalPriceEditText = customView.findViewById<EditText>(R.id.totalPriceED)
        val closeButton = customView.findViewById<Button>(R.id.closePriceDialog)

        /*Костыль для работы кода
        * Нужно вынести инициализацию VM вверх
        * */

        try{
            japanCalcViewModel.setCarPrice(binding.carPriceYenET.text.toString().toInt())

            Log.i("carPrice", binding.carPriceYenET.text.toString())
            val customPayment = japanCalcViewModel.getCustomPrice(getYearRadioButton(),
                binding.engineCapacityED.text.toString().toInt(),
                japanCalcViewModel.carPriceYenLiveData.value!!.toInt(),
                exchangeRateViewModel.getYenRate(),
                exchangeRateViewModel.getEuroRate())

            japanCalcViewModel.init(customPayment)
            japanCalcViewModel.carPriceYenLiveData.observe(viewLifecycleOwner) {
                try{
                    val formInfo = String.format(Locale.GERMANY, "%,d", it.toString().toInt())
                    carPriceEditText.setText("${formInfo} ¥")

                }
                catch (_: Exception){

                }
            }

            japanCalcViewModel.customPaymentLiveData.observe(viewLifecycleOwner) {
                try{
                    val formInfo = String.format(Locale.GERMANY, "%,d", it.roundToInt().toString().toInt())
                    customPriceEditText.setText("$formInfo ₽")
                }
                catch (_: Exception){

                }
            }


            var buff1: String = japanCalcViewModel.calculateFinalPrice(exchangeRateViewModel.getYenRate()).roundToInt().toString()
            val buffEdit1 = String.format(Locale.GERMANY, "%,d", buff1.toInt())
            totalPriceEditText.setText("$buffEdit1 ₽")


            val dialog = builder.create()
            dialog.show()

            closeButton.setOnClickListener{
                dialog.cancel()
            }
        }
        catch (_: Exception){

        }



    }

    fun getYearRadioButton(): Int{
        var result = -1
        binding.apply {
            if(lessThreeRB.isChecked)
                result = 2
            else if(threeFromFiveRB.isChecked)
                result = 4
            else if(fiveFromSevenRB.isChecked)
                result = 6
            else if(moreSevenRB.isChecked)
                result = 8
        }
        return result
    }



    


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JapanCalculatorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


