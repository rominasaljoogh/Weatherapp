package com.example.weatherapp.ui.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.dataBase.DataBase
import com.example.weatherapp.data.db.CurrentWeatherDao
import com.example.weatherapp.data.entity.Current
import com.example.weatherapp.data.entity.ResponseGetWeather
import com.example.weatherapp.data.network.ApiWeather
import com.example.weatherapp.data.network.RetrofitWeatherInstance
import com.example.weatherapp.data.provider.UnitProviderImpl
import com.example.weatherapp.data.provider.UnitSystem
import kotlinx.android.synthetic.main.fragment_current_weather.*
import kotlinx.android.synthetic.main.fragment_current_weather.group_loading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentWeatherFragment : Fragment() {

    var dataBase: DataBase? = null
    var currentDao: CurrentWeatherDao? = null

    var unit : Boolean? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Today"

        val unitProvider = UnitProviderImpl(requireContext()).getUnitSystem()
        if(unitProvider==UnitSystem.METRIC) unit=true

        configureDB()
        getWeather()


    }

    private fun configureDB(){

        dataBase = DataBase.getDataBaseInstance(requireContext())
        currentDao = dataBase?.getCurrentWeatherDao()
    }

    private fun getWeather() {

        val retrofit = RetrofitWeatherInstance.getRetrofitInstance()
        val currentWeatherService = retrofit.create(ApiWeather::class.java)

        val unitapi = chooseUnit("metric", "imperial")

        currentWeatherService.getWeather("35.689198", "51.388973","80f4cf199c6d13111b4d9a31580c3118","$unitapi")
                .enqueue(object : Callback<ResponseGetWeather> {
                    override fun onFailure(call: Call<ResponseGetWeather>, t: Throwable) {

                        group_loading.visibility = View.GONE
                        val cash = currentDao?.getAllCurrentData()
                        if (cash != null) {
                            updateAllCurrent(cash)
                        }

                    }

                    override fun onResponse(
                            call: Call<ResponseGetWeather>,
                            response: Response<ResponseGetWeather>) {

                        group_loading.visibility = View.GONE
                        response.body()?.current?.let { saveAllCurrent(it) }
                        response.body()?.current?.let { updateAllCurrent(it) }
                        response.body()?.let { registerRecyclerh(it) }
                    }

                })
    }

    private fun saveAllCurrent(current: Current) {

        if(current == null){
            currentDao?.insertCurrentWeather(current)
        }
        else{
            currentDao?.deleteAll(current)
            currentDao?.insertCurrentWeather(current)
        }

    }

    private fun updateAllCurrent(currentWeather: Current){

        textView_condition.text = currentWeather.weather[0].description
        updateTemperatures(currentWeather.temp, currentWeather.feelsLike)
        updateWind(currentWeather.windSpeed)
        textView_humidity.text = "Humidity: ${currentWeather.humidity}%"
        updateVisibility(currentWeather.visibility)
        Glide.with(this@CurrentWeatherFragment)
                .load("http://openweathermap.org/img/wn/${currentWeather.weather[0].icon}@2x.png")
                .into(imageView_condition_icon)

    }

    private fun chooseUnit(metric: String, imperial: String): String {
        return if (unit == true) metric else imperial
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double) {
        val unitAbbreviation = chooseUnit("°C", "°F")
        textView_temperature.text = "$temperature $unitAbbreviation"
        textView_feels_like_temperature.text = "Feels like: $feelsLike $unitAbbreviation"
    }

    private fun updateWind(windSpeed: Double) {
        val unitAbbreviation = chooseUnit("m/s", "mph")
        textView_wind.text = "Wind: $windSpeed $unitAbbreviation"
    }

    private fun updateVisibility(visibilityDistance: Int) {
        val unitAbbreviation = chooseUnit("km", "mi.")
        textView_visibility.text = "Visibility: ${visibilityDistance/1000} $unitAbbreviation"
    }

    private fun registerRecyclerh(responseData: ResponseGetWeather){

        recyclerViewHourly.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL , false)
        recyclerViewHourly.adapter = HourlyItemRecyclerViewAdapter (responseData)
    }
}