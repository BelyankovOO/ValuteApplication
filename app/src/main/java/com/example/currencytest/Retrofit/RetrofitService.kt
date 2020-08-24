package com.example.currencytest.Retrofit

import com.example.currencytest.Model.ValCurs
import com.example.currencytest.Model.Valute
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("daily.xml")
    fun getValute(): Call<ValCurs?>

    //@GET("https://www.countryflags.io/{name}/flat/64.png")
    //fun getFlag(@Path("name") name: String ): Call<>

    companion object {
        val instance : RetrofitService by lazy{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.cbr-xml-daily.ru/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
            retrofit.create<RetrofitService>(RetrofitService::class.java)
        }
    }

}