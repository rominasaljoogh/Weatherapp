package com.example.weatherapp.ui.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.ApiCurrentWeather
import com.example.weatherapp.data.dataBase.DataBase
import com.example.weatherapp.data.db.CurrentWeatherDao
import com.example.weatherapp.data.entity.currentweatherModels.ResponseGetCurrentWeather
import com.example.weatherapp.data.retrofitWeatherInstance
import kotlinx.android.synthetic.main.fragment_current_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentWeatherFragment : Fragment() {

    var dataBase: DataBase? = null
    var dataCurrentDao: CurrentWeatherDao? = null

    private lateinit var  navController : NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        getWeather()
        configureDB()


    }

    private fun getWeather() {

        val retrofit = retrofitWeatherInstance.getRetrofitInstance()

        val currentWeatherService = retrofit.create(ApiCurrentWeather::class.java)

        currentWeatherService.getCurrentWeather("tehran", "80f4cf199c6d13111b4d9a31580c3118", "metric")
                .enqueue(object : Callback<ResponseGetCurrentWeather> {
                    override fun onFailure(call: Call<ResponseGetCurrentWeather>, t: Throwable) {

                        group_loading.visibility = View.GONE
                        dataCurrentDao?.getAllWeather()

                    }


                    override fun onResponse(
                            call: Call<ResponseGetCurrentWeather>,
                            response: Response<ResponseGetCurrentWeather>) {

                        group_loading.visibility = View.GONE

                        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"

                        textView_condition.text = response.body()?.weather?.get(0)?.description
                        textView_temperature.text = "${response.body()?.main?.temp} °C"
                        textView_maxandmin_temperature.text = "Feels like: ${response.body()?.main?.feelsLike} °C"
                        textView_wind.text = "Wind: ${response.body()?.wind?.speed} m/sec"
                        textView_humidity.text = "humidity: ${response.body()?.main?.humidity} %"
                        Glide.with(this@CurrentWeatherFragment)
                                .load("http://openweathermap.org/img/wn/${response.body()?.weather?.get(0)?.icon}@2x.png")
                                .into(imageView_condition_icon)
                        dataCurrentDao?.insertAllData(response.body())
                    }

                })
    }

    private fun configureDB(){
        dataBase = DataBase.getDataBaseInstance(requireContext())
        dataCurrentDao = dataBase?.getCurrentWeatherDao()
    }

}
