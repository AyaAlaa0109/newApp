package com.example.weatherapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.AllResponse
import com.example.weatherapplication.repo.WeatherRepo

class DailyViewModel : ViewModel() {
    var weatherLiveData = MutableLiveData<AllResponse>()
    private val repo = WeatherRepo();

    fun getWeather(lat: Double,lon:Double,exclude:String,lang:String,units: String) {
        repo.getWeather(lat,lon,exclude,lang,units)
        weatherLiveData = repo.weatherLiveData

    }
}