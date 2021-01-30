package com.example.weatherapp.ui.weather.future

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.provider.UnitProviderImpl
import com.example.weatherapp.data.provider.UnitSystem
import kotlinx.android.synthetic.main.fragment_current_weather.*
import kotlinx.android.synthetic.main.fragment_forecast7_day_details.*
import kotlinx.android.synthetic.main.fragment_forecast7_day_details.imageView_condition_icon
import kotlinx.android.synthetic.main.fragment_forecast7_day_details.textView_condition
import kotlinx.android.synthetic.main.fragment_forecast7_day_details.textView_temperature
import kotlinx.android.synthetic.main.fragment_forecast7_day_details.textView_wind


class Forecast7DayDetailsFragment : Fragment() {

    private val args: Forecast7DayDetailsFragmentArgs by navArgs()

    private lateinit var  navController : NavController

    var unit : Boolean? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast7_day_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val unitProvider = UnitProviderImpl(requireContext()).getUnitSystem()
        if(unitProvider == UnitSystem.METRIC) unit=true


        updateui()

    }

    private fun updateui(){

        updateTemperatures()
        textView_condition.text = args.Daily.weather[0].description
        updateWind()
        textView_precipitation.text = "Precipitation: ${args.Daily.rain} "
        textView_pressure.text = "Pressure: ${args.Daily.pressure}"
        textView_uv.text = "UV: ${args.Daily.uvi}"
        Glide.with(this@Forecast7DayDetailsFragment)
            .load("http://openweathermap.org/img/wn/${args.Daily.weather[0].icon}@2x.png")
            .into(imageView_condition_icon)
    }

    private fun chooseUnit(metric: String, imperial: String): String {
        return if (unit == true) metric else imperial
    }

    private fun updateTemperatures() {
        val unitAbbreviation = chooseUnit("°C", "°F")
        textView_temperature.text = "${args.Daily.temp.day} $unitAbbreviation"
        textView_min_max_temperature.text = "Max: ${args.Daily.temp.max} $unitAbbreviation Min: ${args.Daily.temp.min} $unitAbbreviation"
    }

    private fun updateWind() {
        val unitAbbreviation = chooseUnit("m/s", "mph")
        textView_wind.text = "Wind: ${args.Daily.windSpeed} $unitAbbreviation"
    }

}