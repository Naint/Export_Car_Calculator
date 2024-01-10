package com.example.japancars.screens.japan

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.japancars.CustomsPayment
import com.example.japancars.PhisycalCustomPayment
import com.example.japancars.R
import com.example.japancars.screens.japan.JapanCalcViewModel
import com.example.japancars.databinding.FragmentJapanCalculatorBinding
import java.text.DecimalFormat
import java.util.Locale

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class JapanCalculatorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentJapanCalculatorBinding? = null
    private val binding get() = _binding!!

    private lateinit var japanCalcViewModel: JapanCalcViewModel

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calculateButton.setOnClickListener{

            showPriceDialog()
        }

    }


    private fun showPriceDialog(){
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.price_dialog, null)
        //customView.background = ColorDrawable(Color.TRANSPARENT)
        builder.setView(customView)

        try{
            var info = binding.carPriceYenET.text
            var formInfo = String.format(Locale.GERMANY, "%,d", info.toString().toInt())
            val carPriceEditText = customView.findViewById<EditText>(R.id.carPriceInfo)
            carPriceEditText.setText("${formInfo}¥")
        }
        catch (e: Exception){

        }


        val dialog = builder.create()
        dialog.show()

        var x: PhisycalCustomPayment = PhisycalCustomPayment()
        Log.i("TAM", x.calculatePaymentThreeToFive(1500).toString())





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


