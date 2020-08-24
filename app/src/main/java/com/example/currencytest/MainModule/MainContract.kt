package com.example.currencytest.MainModule

import com.example.currencytest.ViewModel.ViewModel

interface MainContract{
    interface View {
        fun setData(data: List<ViewModel.ValuteSummaryViewData>)
        fun showMessage(message: String)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter {
        fun round(number: Double?, count: Int): Double
        fun swapToHeader(item: ViewModel.ValuteSummaryViewData?, data: MutableList<ViewModel.ValuteSummaryViewData>): List<ViewModel.ValuteSummaryViewData>
        fun viewDidLoad()
        fun valuteCount(mainValute: ViewModel.ValuteSummaryViewData?, data: List<ViewModel.ValuteSummaryViewData>): List<ViewModel.ValuteSummaryViewData>
        fun firstInstance(data: List<ViewModel.ValuteSummaryViewData>):List<ViewModel.ValuteSummaryViewData>
    }

    interface Interactor {
        fun setup()
        fun loadValute(callback: (List<ViewModel.ValuteSummaryViewData>?) -> Unit)
        fun updateList(list: List<ViewModel.ValuteSummaryViewData>)
        fun getStatusOfInstance(): Boolean
    }

    interface Router {

    }

    interface Configurator{
        fun configurate(view: MainActivity)
    }

    interface InteractorOutput{
        fun onRequestSuccess(result: List<ViewModel.ValuteSummaryViewData>)
        fun onRequestError()
    }
}