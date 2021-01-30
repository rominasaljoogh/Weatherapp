package com.example.weatherapp.ui.weather.future

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.Daily
import kotlinx.android.synthetic.main.fragment_forecast7_day.view.*
import java.text.SimpleDateFormat

class MyItemRecyclerViewAdapter(
    private val responseData: List<Daily>,
    val unitAbbreviatio: String,
    private val onclick : (Daily) -> Unit
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_forecast7_day, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textViewCondition.text = responseData[position].weather[0].description

        val dateF : SimpleDateFormat = SimpleDateFormat("E d/M")
        holder.textViewDate.text = dateF.format((responseData[position].dt)*1000)

        holder.textViewTemperature.text = "${responseData[position].temp.day} $unitAbbreviatio"
        Glide.with(holder.itemView.context)
                .load("http://openweathermap.org/img/wn/${responseData[position].weather[0].icon}@2x.png")
                .into(holder.imageViewConditionIcon)

        holder.itemView.setOnClickListener{
            onclick(responseData[position])
          }
        }

    override fun getItemCount(): Int = responseData.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textViewCondition: TextView = view.textView_condition
        var textViewDate: TextView = view.textView_date
        var textViewTemperature: TextView = view.textView_temperature
        var imageViewConditionIcon: ImageView = view.imageView_condition_icon
    }
}