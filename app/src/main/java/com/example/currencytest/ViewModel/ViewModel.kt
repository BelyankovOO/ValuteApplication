package com.example.currencytest.ViewModel

import android.app.Application
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.currencytest.Model.ValCurs
import com.example.currencytest.Model.Valute
import com.example.currencytest.Repository.Repository
import kotlinx.android.parcel.Parcelize
import java.text.NumberFormat
import java.util.*

class ViewModel(application: Application): AndroidViewModel(application) {
    var valuteRepository: Repository? = null
    var activeValuteList: List<ValuteSummaryViewData>? = null
    var notFirstInstance: Boolean = false

    data class ValuteSummaryViewData(
        var CharCode: String? = "",
        //var Nominal: Int? = 0,
        //var Name: String? = "",
        //var Value: Double? = 0.0,
        var RealValue: Double? = 0.0
    )

    private fun valuteToValuteSummaryViewData(valute: Valute?): ValuteSummaryViewData{
        return ValuteSummaryViewData(
            valute?.CharCode,
            //valute?.Nominal,
            //valute?.Value?.replace(",", ".")?.toDouble(),
            valute?.Value?.replace(",", ".")?.toDouble()?.div(valute.Nominal)
        )
    }

    fun getValute(callback: (List<ValuteSummaryViewData>?) -> Unit){
        if(activeValuteList == null){
            val valuteRepository = valuteRepository ?: return
            valuteRepository.getValute {
                val searchView = it?.valute?.map{
                    valuteToValuteSummaryViewData(it)
                }
                setList(activeValuteList)
                callback(searchView)
            }
        } else {
            notFirstInstance = true
            callback(activeValuteList)
        }
    }

    fun setList(list: List<ValuteSummaryViewData>?){
        activeValuteList = list
    }

    fun getStatusOfInstance(): Boolean{
        return notFirstInstance
    }
}