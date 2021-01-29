package com.example.weatherapp.ui.weather.current

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.ResponseGetWeather
import kotlinx.android.synthetic.main.fragment_current_item_hourly.view.*
import java.text.SimpleDateFormat

class HourlyItemRecyclerViewAdapter (
        private val responseData: ResponseGetWeather
//    , private val onclick : (ResponseGetFutureWeather) -> Unit
) : RecyclerView.Adapter<HourlyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_current_item_hourly, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView_conditionh.text = responseData.hourly[position].weather[0].description

        val dateFh : SimpleDateFormat = SimpleDateFormat("h:m")
        holder.textView_dateh.text = dateFh.format((responseData.hourly[position].dt)*1000)

        holder.textView_temperatureh.text = responseData.hourly[position].temp.toString()
        Glide.with(holder.itemView.context)
                .load("http://openweathermap.org/img/wn/${responseData.hourly[position].weather[0].icon}@2x.png")
                .into(holder.imageView_condition_iconh)

//        holder.itemView.setOnClickListener{
//            onclick(data[position])
//          }
    }

    override fun getItemCount(): Int = responseData.daily.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textView_conditionh: TextView = view.textView_conditionh
        var textView_dateh: TextView = view.textView_dateh
        var textView_temperatureh: TextView = view.textView_temperatureh
        var imageView_condition_iconh = view.imageView_condition_iconh
    }
}