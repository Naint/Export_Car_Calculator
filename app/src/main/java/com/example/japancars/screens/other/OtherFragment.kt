package com.example.japancars.screens.other

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.japancars.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import kotlin.reflect.typeOf

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class OtherFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getInfoWeb()

    }

    private fun getInfoWeb(){

        GlobalScope.launch {
            val doc =
                Jsoup.connect(
                    "https://www.encar.com/dc/dc_cardetailview.do?pageid=dc_carsearch&listAdvType=pic&carid=36719986&view_type=hs_ad&wtClick_korList=015&advClickPosition=kor_pic_p1_g5")
                    .get()


            Log.i("doc", doc.text())
            val table = doc.select("span.red")
            Log.i("SELECT", table.text())
            val table1 = doc.getElementsByTag("div")
            Log.i("getBy", table1.text())


            /*val rateTable = doc.getElementById("currencyTab1").text().toString().split(" ")
            Log.i("euro", rateTable[9])
            Log.i("yen", rateTable[18])*/
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OtherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}