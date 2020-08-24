package com.example.currencytest.MainModule

import androidx.lifecycle.ViewModelProvider
import com.example.currencytest.Repository.Repository
import com.example.currencytest.Retrofit.RetrofitService
import com.example.currencytest.Retrofit.ValuteService
import com.example.currencytest.ViewModel.ViewModel

class MainInteractor(private var activity: MainActivity): MainContract.Interactor {
    private lateinit var viewModel: ViewModel
    private lateinit var valuteService: ValuteService

    override fun setup() {
        valuteService = ValuteService(RetrofitService.instance)
        viewModel = ViewModelProvider(activity).get(ViewModel::class.java)
        viewModel.valuteRepository = Repository(valuteService)
    }

    override fun loadValute(callback: (List<ViewModel.ValuteSummaryViewData>?) -> Unit){
        viewModel.getValute {
            //println("interactor worked $it")
            callback(it)
        }
    }

    override fun updateList(list: List<ViewModel.ValuteSummaryViewData>) {
        viewModel.setList(list)
    }

    override fun getStatusOfInstance(): Boolean {
        return viewModel.getStatusOfInstance()
    }
}