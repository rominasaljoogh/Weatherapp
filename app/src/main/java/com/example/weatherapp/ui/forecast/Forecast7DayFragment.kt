package com.example.weatherapp.ui.forecast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.data.ApiForecastWeather
import com.example.weatherapp.data.dataBase.DataBase
import com.example.weatherapp.data.db.CurrentWeatherDao
import com.example.weatherapp.data.db.ForecastWeatherDao
import com.example.weatherapp.data.entity.FutureWetherModels.ResponseGetFutureWeather
import com.example.weatherapp.data.entity.forecastweatherModels.ResponseGetForecastWeather
import com.example.weatherapp.data.network.RetrofitWeatherInstance
import kotlinx.android.synthetic.main.fragment_forecast7_day_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Forecast7DayFragment : Fragment() {

    var dataBase: DataBase? = null
    var dataFutureDao: ForecastWeatherDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast7_day_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "7 Days"

        getWeather()

    }

    private fun getWeather() {

        val retrofit = RetrofitWeatherInstance.getRetrofitInstance()
        val forcastWeatherService = retrofit.create(ApiForecastWeather::class.java)

        forcastWeatherService.getForecastWeather("33.441792", "-94.037689","80f4cf199c6d13111b4d9a31580c3118",
                                                    "daily","metric")
                .enqueue(object : Callback<ResponseGetFutureWeather> {
                    override fun onFailure(call: Call<ResponseGetFutureWeather>, t: Throwable) {

                        t.message?.let { Log.e("TESTEST" , it) }

                    }

                    override fun onResponse(call: Call<ResponseGetFutureWeather>,
                                            response: Response<ResponseGetFutureWeather>) {

                        group_loading.visibility = View.GONE
                        Log.e("TESTEST" , response.body().toString())
                        response.body()?.let { registerRecycler(it) }

                    }

                })
    }

    private fun registerRecycler(responseData:ResponseGetFutureWeather){

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL , false)
        recyclerView.adapter = MyItemRecyclerViewAdapter (responseData)
    }

}