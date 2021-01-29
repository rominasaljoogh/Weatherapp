package com.example.weatherapp.ui.weather.future

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.data.network.ApiWeather
import com.example.weatherapp.data.entity.ResponseGetWeather
import com.example.weatherapp.data.network.RetrofitWeatherInstance
import kotlinx.android.synthetic.main.fragment_forecast7_day_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Forecast7DayFragment : Fragment() {

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
        val forecastWeatherService = retrofit.create(ApiWeather::class.java)

        forecastWeatherService.getWeather("33.441792", "-94.037689","80f4cf199c6d13111b4d9a31580c3118","metric")
                .enqueue(object : Callback<ResponseGetWeather> {
                    override fun onFailure(call: Call<ResponseGetWeather>, t: Throwable) {

                        t.message?.let { Log.e("TESTEST" , it) }

                    }

                    override fun onResponse(call: Call<ResponseGetWeather>,
                                            response: Response<ResponseGetWeather>) {

                        group_loading.visibility = View.GONE
                        response.body()?.let { registerRecycler(it) }

                    }

                })
    }

    private fun registerRecycler(responseData: ResponseGetWeather){

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL , false)
        recyclerView.adapter = MyItemRecyclerViewAdapter (responseData)
    }

}