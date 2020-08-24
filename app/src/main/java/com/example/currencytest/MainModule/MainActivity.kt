package com.example.currencytest.MainModule

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencytest.Adapter.MainAdapter
import com.example.currencytest.R
import com.example.currencytest.Repository.Repository
import com.example.currencytest.Retrofit.RetrofitService
import com.example.currencytest.Retrofit.ValuteService
import com.example.currencytest.ViewModel.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.math.RoundingMode

import java.nio.charset.Charset


class MainActivity : AppCompatActivity(), MainAdapter.ListAdapterListener, MainContract.View{

    var configurator: MainContract.Configurator? = null
    var presenter: MainContract.Presenter? = null
    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(configurator == null) configurator = MainConfigurator()
        configurator?.configurate(this)

        mainRecyclerView = findViewById(R.id.main_recyclerview)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter(null, this, this)
        mainRecyclerView.adapter = mainAdapter

        presenter?.viewDidLoad()
    }

    override fun onShowDetails(newMainValute: ViewModel.ValuteSummaryViewData, data: List<ViewModel.ValuteSummaryViewData>) {
        mainAdapter.setData(presenter?.valuteCount(newMainValute, data))
    }

    override fun setData(data: List<ViewModel.ValuteSummaryViewData>) {
        mainAdapter.setData(data)
    }

    override fun showProgressBar() {
        mainRecyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        mainRecyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}