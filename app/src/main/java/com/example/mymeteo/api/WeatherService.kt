package com.example.mymeteo.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("q") cityCountry: String,@Query("APPID") app_id:String,@Query("units") units:String ,@Query("lang") lang:String): Call<WeatherResponse>
}