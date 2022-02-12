package com.example.weatherapplication.data.remote

import android.telecom.Call
import com.example.example.AllResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
////current
//    @GET("data/2.5/weather")
//    suspend   fun getWeather(
//        @Query("appid") key: String,
//        @Query("lat") lat: Int,
//        @Query("lon") lon: Int,
//        @Query("lang") lang: String
//   , @Query("units") units: String
//
//        ): Response<CurrentWeatherResponse>
//https://api.openweathermap.org/data/2.5/onecall?lat=33.44&lon=-94.04&exclude=minutely,hourly&appid=70368513d67cf9b30e258e58
@GET("data/2.5/onecall")
suspend   fun getAllWeather(
    @Query("lat") lat: Double,
    @Query("lon") lon: Double,
     @Query("exclude") exclude: String,
    @Query("lang") lang: String,
     @Query("units") units: String,
    @Query("appid") key: String

): Response<AllResponse>

}