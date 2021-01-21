package com.example.weatherapp.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.weatherapp.R


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

    }

    private fun registerRecycler(){

        //noteList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL , false)
        //noteList.adapter = MyItemRecyclerViewAdapter (data as MutableList<ResponseGetForecastWeather>){}

    }

}