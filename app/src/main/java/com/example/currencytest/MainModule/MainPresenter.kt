package com.example.currencytest.MainModule

import com.example.currencytest.ViewModel.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class MainPresenter(private var view: MainActivity): MainContract.Presenter, MainContract.InteractorOutput {
    var router: MainContract.Router? = null
    var interactor: MainContract.Interactor? = null

    override fun viewDidLoad() {
        view.showProgressBar()
        interactor?.setup()
        interactor?.loadValute {result ->
            if(result != null){
                onRequestSuccess(result)
            } else {
                onRequestError()
            }
        }
    }

    override fun valuteCount(mainValute: ViewModel.ValuteSummaryViewData?, data: List<ViewModel.ValuteSummaryViewData>): List<ViewModel.ValuteSummaryViewData>{
        val changedList: ArrayList<ViewModel.ValuteSummaryViewData> = ArrayList()
        mainValute?.RealValue?.let { realvalute ->
            data.map { element ->
                //println("${element.RealValue}, $realvalute")
                element.RealValue = round(element.RealValue?.div(realvalute), 4)
                //println("result ${element.RealValue}")
                changedList.add(element)
            }
        }
        val swappedList = swapToHeader(mainValute, changedList.toMutableList())
        println("swappedlist $swappedList")
        interactor?.updateList(swappedList)
        return swappedList
    }

    override fun firstInstance(data: List<ViewModel.ValuteSummaryViewData>): List<ViewModel.ValuteSummaryViewData> {
        if(interactor?.getStatusOfInstance() == false) {
            println("status false")
            return valuteCount(data.find { it.CharCode == "USD" }, data)
        } else {
            println("status true $data")
            return data
        }
    }


    override fun round(number: Double?, count: Int): Double{
        var bd = BigDecimal(number.toString())
        bd = bd.setScale(count, RoundingMode.HALF_EVEN)
        return bd.toDouble()
    }

    override fun swapToHeader(item: ViewModel.ValuteSummaryViewData?, data: MutableList<ViewModel.ValuteSummaryViewData>): List<ViewModel.ValuteSummaryViewData>{
        if(item == null) return data
        else {
            data.removeAt(data.indexOf(item))
            data.add(0, item)
            return data
        }
    }

    override fun onRequestSuccess(result: List<ViewModel.ValuteSummaryViewData>) {
        val dataToSet = firstInstance(result)
        view.hideProgressBar()
        view.setData(dataToSet)
    }

    override fun onRequestError() {
        view.hideProgressBar()
        view.showMessage("InstanceError")
    }


}