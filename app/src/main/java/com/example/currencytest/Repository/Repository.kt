package com.example.currencytest.Repository

import com.example.currencytest.Model.ValCurs
import com.example.currencytest.Retrofit.ValuteService

class Repository(private val valuteService: ValuteService) {
    fun getValute(callback: (ValCurs?) -> Unit){
        valuteService.getCategory {
            callback(it)
        }
    }
}