package com.example.currencytest.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.currencytest.R
import com.example.currencytest.ViewModel.ViewModel
import java.util.*

class MainAdapter(private var summaryViewList: List<ViewModel.ValuteSummaryViewData>?,
                  private val listAdapterListener: ListAdapterListener,
                  private val parentActivity: Activity): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    interface ListAdapterListener{
        fun onShowDetails(newMainValute: ViewModel.ValuteSummaryViewData, data: List<ViewModel.ValuteSummaryViewData>)
    }

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val charName: TextView? = v.findViewById(R.id.char_val)
        val valueVal: TextView? = v.findViewById(R.id.value_val)
        val flagImage: ImageView? = v.findViewById(R.id.flag_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView: View = when(viewType){
            TypeOfView.FIRST.ordinal -> layoutInflater.inflate(R.layout.main_recycler_view_header, parent,false)
            else -> layoutInflater.inflate(R.layout.main_recyclerview_item, parent,false)
        }
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searchViewList = summaryViewList ?: return
        val searchView = searchViewList[position]
        holder.charName?.text = searchView.CharCode
        holder.flagImage?.let{imageView ->
            Glide.with(parentActivity)
                .load("https://www.countryflags.io/${searchView.CharCode?.toLowerCase(Locale.US)?.substring(0,2)}/flat/64.png")
                .into(imageView)
        }
        holder.valueVal?.text = searchView.RealValue.toString()
        holder.itemView.setOnClickListener {
            listAdapterListener.onShowDetails(searchView, searchViewList)
        }
    }

    override fun getItemCount(): Int {
       return summaryViewList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        if(position == 0) return TypeOfView.FIRST.ordinal
        else return TypeOfView.OTHER.ordinal
    }

    fun setData(list: List<ViewModel.ValuteSummaryViewData>?){
        summaryViewList = list
        this.notifyDataSetChanged()
    }

    fun getList(): List<ViewModel.ValuteSummaryViewData>?{
        return summaryViewList
    }


}