package com.example.weatherapp.ui.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.dataBase.DataBase
import com.example.weatherapp.data.db.CurrentWeatherDao
import com.example.weatherapp.data.entity.Current
import com.example.weatherapp.data.entity.ResponseGetWeather
import com.example.weatherapp.data.network.ApiWeather
import com.example.weatherapp.data.network.RetrofitWeatherInstance
import com.example.weatherapp.data.provider.UnitProvider
import com.example.weatherapp.data.provider.UnitSystem
import kotlinx.android.synthetic.main.fragment_current_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentWeatherFragment : Fragment() {

    var dataBase: DataBase? = null
    var currentDao: CurrentWeatherDao? = null

    private lateinit var unitProvider : UnitProvider

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Today"

        getWeather()
        //configureDB()

    }

    private fun configureDB(){

        dataBase = DataBase.getDataBaseInstance(requireContext())
        currentDao = dataBase?.getCurrentWeatherDao()
    }

    private fun getWeather() {

        val retrofit = RetrofitWeatherInstance.getRetrofitInstance()
        val currentWeatherService = retrofit.create(ApiWeather::class.java)

        currentWeatherService.getWeather("33.441792", "-94.037689","80f4cf199c6d13111b4d9a31580c3118","metric")
                .enqueue(object : Callback<ResponseGetWeather> {
                    override fun onFailure(call: Call<ResponseGetWeather>, t: Throwable) {

                        //group_loading.visibility = View.GONE

                        //currentDao?.getCurrentWeather()

                    }

                    override fun onResponse(
                            call: Call<ResponseGetWeather>,
                            response: Response<ResponseGetWeather>) {

                        group_loading.visibility = View.GONE

                        //saveAllNewData(response.body()?.current)

                        textView_condition.text = response.body()?.current?.weather?.get(0)?.description
                        updateTemperatures(response.body()?.current!!.temp, response.body()?.current!!.feelsLike)
                        updateWind(response.body()?.current!!.windSpeed)
                        textView_humidity.text = "Humidity: ${response.body()?.current?.humidity}%"
                        updateVisibility(response.body()?.current!!.visibility)
                        Glide.with(this@CurrentWeatherFragment)
                                .load("http://openweathermap.org/img/wn/${response.body()?.current?.weather?.get(0)?.icon}@2x.png")
                                .into(imageView_condition_icon)
                    }

                })
    }

    private fun saveAllNewData(current: Current?) {

        if(current != null){
            currentDao?.insertCurrentWeather(current)
        }

    }

    private fun chooseUnit(metric: String, imperial: String): String {
        return if (unitProvider.getUnitSystem() == UnitSystem.METRIC) metric else imperial
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double) {
        //val unitAbbreviation = chooseUnit("°C", "°F")
        textView_temperature.text = "$temperature"
        textView_feels_like_temperature.text = "Feels like: $feelsLike"
    }

    private fun updateWind(windSpeed: Double) {
        //val unitAbbreviation = chooseUnit("kph", "mph")
        textView_wind.text = "Wind: $windSpeed"
    }

    private fun updateVisibility(visibilityDistance: Int) {
        //val unitAbbreviation = chooseUnit("km", "mi.")
        textView_visibility.text = "Visibility: $visibilityDistance"
    }
}
