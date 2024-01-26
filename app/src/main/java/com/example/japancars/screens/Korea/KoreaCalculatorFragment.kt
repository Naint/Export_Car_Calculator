package com.example.japancars.screens.Korea

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.japancars.R
import com.example.japancars.databinding.FragmentJapanCalculatorBinding
import com.example.japancars.databinding.FragmentKoreaCalculatorBinding
import com.example.japancars.screens.ExchangeRateViewModel
import java.util.Locale
import kotlin.math.roundToInt


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class KoreaCalculatorFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var koreaCalcViewModel: KoreaCalcViewModel
    private lateinit var exchangeRateViewModel: ExchangeRateViewModel

    private var _binding : FragmentKoreaCalculatorBinding? = null
    private val binding get() = _binding!!

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

        koreaCalcViewModel = ViewModelProvider(this)[KoreaCalcViewModel::class.java]
        exchangeRateViewModel = ViewModelProvider(this)[ExchangeRateViewModel::class.java]

        _binding = FragmentKoreaCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateButton.setOnClickListener{
            showPriceDialog()
        }






        //Log.i("KoreaInfo", koreaCalcViewModel.calculateFinalPrice(0.066369).toString())
    }

    private fun showPriceDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.price_dialog, null)
        builder.setView(customView)

        val carPriceEditText = customView.findViewById<EditText>(R.id.carPriceInfo)
        val customPriceEditText = customView.findViewById<EditText>(R.id.customPriceED)
        val totalPriceEditText = customView.findViewById<EditText>(R.id.totalPriceED)
        val closeButton = customView.findViewById<Button>(R.id.closePriceDialog)

        /*Костыль для работы кода
        * Нужно вынести инициализацию VM вверх
        * */

        try{
            koreaCalcViewModel.setCarPrice(binding.carVonPrice.text.toString().toInt())

            Log.i("carPrice", binding.carVonPrice.text.toString())
            val customPayment = koreaCalcViewModel.getCustomPrice(getYearRadioButton(),
                binding.engineCapacityED.text.toString().toInt(),
                koreaCalcViewModel.carPriceVonLiveData.value!!.toInt(),
                0.066369,
                exchangeRateViewModel.getEuroRate())

            koreaCalcViewModel.setCustomPayment(customPayment)
            koreaCalcViewModel.carPriceVonLiveData.observe(viewLifecycleOwner) {
                try{
                    val formInfo = String.format(Locale.GERMANY, "%,d", it.toString().toInt())
                    carPriceEditText.setText("${formInfo}₩")

                }
                catch (_: Exception){

                }
            }

            koreaCalcViewModel.customPaymentLiveData.observe(viewLifecycleOwner) {
                try{
                    val formInfo = String.format(Locale.GERMANY, "%,d", it.roundToInt().toString().toInt())
                    customPriceEditText.setText("$formInfo ₽")
                }
                catch (_: Exception){

                }
            }


            //var buff1: String = koreaCalcViewModel.calculateFinalPrice(exchangeRateViewModel.getYenRate()).roundToInt().toString()
            val buff1: String = koreaCalcViewModel.calculateFinalPrice(0.066369).roundToInt().toString()
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
            KoreaCalculatorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}