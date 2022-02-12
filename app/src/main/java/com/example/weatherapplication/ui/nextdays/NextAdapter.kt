package com.example.weatherapplication.ui.nextdays

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.Daily
import com.example.example.Hourly
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.ItemCurrentHoursBinding
import com.example.weatherapplication.databinding.ItemNextBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class NextAdapter(val currentDailyList: ArrayList<Daily>, val context: Context) :
    RecyclerView.Adapter<NextAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_next,
                parent,
                false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val daily: Daily = currentDailyList.get(position)
        //holder.itemBinding.TestModel(product)
       Log.i("ggggggggggggggggggggnn", "onBindViewHolder: "+daily.temp.toString())
       var link =
            "http://openweathermap.org/img/wn/" + (daily.weather.get(0).icon) + "@2x.png"

        Glide.with(context).load(link).into(holder.itemBinding.imgIcon)
        //
        holder.itemBinding.currentTempMin.setText(daily.temp?.min.toString()+" C")
        holder.itemBinding.currentTempMax.setText(daily.temp?.max.toString()+" C")
        holder.itemBinding.currentPressure.setText("pressure: "+daily.pressure?.toString())
        holder.itemBinding.currentClouds.setText("clouds: "+daily.clouds?.toString()+"%")
        holder.itemBinding.currentHumedity.setText("humidity : "+daily.humidity?.toString()+"%")
        holder.itemBinding.currentWind.setText("wind: "+daily.windSpeed?.toString())
        holder.itemBinding.statusCurrent.setText(daily.weather.get(0).description.toString())
        holder.itemBinding.nextDate.text= getDateTime( daily.dt.toString())




    }

    override fun getItemCount(): Int {
        return currentDailyList.size
    }


    private fun getDateTime(s: String): String? {
        //dd/MM/yyyy hh:mm:ss
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date((s).toLong() * 1000)
            return sdf.format(netDate)

        } catch (e: Exception) {
            return e.toString()
        }


    }


    class ViewHolder(itemBinding: ItemNextBinding) :
        RecyclerView.ViewHolder(itemBinding.getRoot()) {
        var itemBinding: ItemNextBinding

        init {
            this.itemBinding = itemBinding
        }
    }

}