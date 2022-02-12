package com.example.weatherapplication.viewmodels

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.AllResponse
import com.example.weatherapplication.repo.WeatherRepo
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class WeatherViewModel: ViewModel() {


    var weatherLiveData = MutableLiveData<AllResponse>()

    private val repo = WeatherRepo()
    fun getWeather( lat: Double,lon:Double,exclude:String,lang:String,units: String) {
                repo.getWeather(lat,lon,exclude,lang,units)
                weatherLiveData = repo.weatherLiveData

}}