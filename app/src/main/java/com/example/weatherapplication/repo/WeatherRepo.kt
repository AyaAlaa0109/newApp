package com.example.weatherapplication.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.example.AllResponse
import com.example.weatherapplication.constant.API_KEY
import com.example.weatherapplication.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WeatherRepo {

//    private val TAG = "WeatherRepo"
//    val weatherLiveData = MutableLiveData<CurrentWeatherResponse>()
//    fun getWeather(lat: Int,lon:Int,lang:String,units: String) {
//
//        GlobalScope.launch(Dispatchers.IO) {
//            val response = RetrofitClient.getApiService().getWeather(API_KEY, lat,lon,lang,units)
//            withContext(Dispatchers.Main){
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        Log.d(TAG, "onResponse: ${it}")
//                        Log.i(TAG, "getWeather: "+response.body().toString())
//                        weatherLiveData.value = it
//                    }
//                }
//                else{
//                    Log.i(TAG, "getWeather: "+response.errorBody())
//
//
//                }
//            }
//
//    }
//}

    private val TAG = "WeatherRepo"
    val weatherLiveData = MutableLiveData<AllResponse>()
    fun getWeather(lat: Double,lon:Double,exclude:String,lang:String,units: String) {

        GlobalScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.getApiService().getAllWeather(lat,lon,exclude,lang,units,API_KEY)
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d(TAG, "onResponse: ${it}")
                        Log.i(TAG, "getWeather: "+response.body().toString())
                        weatherLiveData.value = it
                    }
                }
                else{
                    Log.i(TAG, "getWeather: "+response.errorBody())


                }
            }

        }
    }

}