package com.example.currencytest.Retrofit

import android.util.Log
import com.example.currencytest.Model.ValCurs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ValuteService(private val valuteService: RetrofitService) {

    fun getCategory(callBack: (ValCurs?) -> Unit) {
        val Call = valuteService.getValute()

        Call.enqueue(object : Callback<ValCurs?> {
            override fun onFailure(call: Call<ValCurs?>, t: Throwable?) {
                Log.d("ValuteService", t!!.message)
                callBack(null)
            }

            override fun onResponse(call: Call<ValCurs?>, response: Response<ValCurs?>) {
                val code = response.code()
                if (response.isSuccessful()) {
                    val body = response.body() ?: return
                    //println(body)
                    callBack(body)
                } else {
                    Log.d("ValuteService", "Code: $code")
                    callBack(null)
                }
            }
        })
    }

}