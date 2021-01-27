package com.example.weatherapp.ui.forecast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.data.ApiForecastWeather
import com.example.weatherapp.data.entity.forecastweatherModels.ResponseGetForecastWeather
import com.example.weatherapp.data.retrofitWeatherInstance
import kotlinx.android.synthetic.main.fragment_forecast7_day_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Forecast7DayFragment : Fragment() {

    private lateinit var  navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast7_day_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        getWeather()

    }

    private fun getWeather() {

        val retrofit = retrofitWeatherInstance.getRetrofitInstance()

        val forcastWeatherService = retrofit.create(ApiForecastWeather::class.java)


        forcastWeatherService.getForecastWeather("tehran", "80f4cf199c6d13111b4d9a31580c3118",7, "metric")
                .enqueue(object : Callback<ResponseGetForecastWeather> {
                    override fun onFailure(call: Call<ResponseGetForecastWeather>, t: Throwable) {

                        t.message?.let { Log.e("TESTEST" , it) }

                    }

                    override fun onResponse(call: Call<ResponseGetForecastWeather>,
                                            response: Response<ResponseGetForecastWeather>) {

                        group_loading.visibility = View.GONE
                        response.body()?.let { registerRecycler(it) }

                    }

                })
    }

    private fun registerRecycler(data:ResponseGetForecastWeather){

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL , false)
        recyclerView.adapter = MyItemRecyclerViewAdapter (data)
    }

}