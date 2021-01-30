package com.example.weatherapp.ui.weather.future

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.data.dataBase.DataBaseforFuture
import com.example.weatherapp.data.db.FutureWeatherDao
import com.example.weatherapp.data.entity.Daily
import com.example.weatherapp.data.entity.ResponseGetWeather
import com.example.weatherapp.data.network.ApiWeather
import com.example.weatherapp.data.network.RetrofitWeatherInstance
import com.example.weatherapp.data.provider.UnitProviderImpl
import com.example.weatherapp.data.provider.UnitSystem
import kotlinx.android.synthetic.main.fragment_forecast7_day_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Forecast7DayFragment : Fragment() {

    var dataBase: DataBaseforFuture? = null
    var currentDao: FutureWeatherDao? = null

    var unit : Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forecast7_day_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.supportActionBar?.title = "7 Days"

        val unitProvider = UnitProviderImpl(requireContext()).getUnitSystem()
        if(unitProvider == UnitSystem.METRIC) unit=true

        getWeather()
        configureDB()
    }

    private fun configureDB(){
        dataBase = DataBaseforFuture.getDataBaseforFutureInstance(requireContext())
        currentDao = dataBase?.getFutureWeatherDao()
    }

    private fun getWeather() {

        val retrofit = RetrofitWeatherInstance.getRetrofitInstance()
        val forecastWeatherService = retrofit.create(ApiWeather::class.java)

        val unitapi = chooseUnit("metric", "imperial")

        forecastWeatherService.getWeather("33.441792", "-94.037689","80f4cf199c6d13111b4d9a31580c3118",unitapi)
                .enqueue(object : Callback<ResponseGetWeather> {
                    override fun onFailure(call: Call<ResponseGetWeather>, t: Throwable) {

                        group_loading.visibility = View.GONE
                        val cash = currentDao?.getAllFutureData()
                        if (cash != null) {
                            registerRecycler(cash)
                        }


                    }

                    override fun onResponse(call: Call<ResponseGetWeather>,
                                            response: Response<ResponseGetWeather>) {

                        group_loading.visibility = View.GONE
                        response.body()?.daily?.let { registerRecycler(it) }
                        response.body()?.daily?.let { saveAllCurrent(it) }

                    }

                })
    }

    private fun saveAllCurrent(daily: List<Daily>) {

        if(daily == null){
            currentDao?.insertFutureWeather(daily)
        }
        else{
            //currentDao?.deleteAllF(daily)
            //currentDao?.insertFutureWeather(daily)
        }

    }

    private fun registerRecycler(responseData: List<Daily>){

        recyclerViewDaily.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL , false)
        recyclerViewDaily.adapter = MyItemRecyclerViewAdapter (responseData)
    }

    private fun chooseUnit(metric: String, imperial: String): String {
        return if (unit == true) metric else imperial
    }

}