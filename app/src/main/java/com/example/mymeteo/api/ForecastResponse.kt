package com.example.mymeteo.api

import com.google.gson.annotations.SerializedName
class ForecastResponse {
    @SerializedName("cod")
    var cod: Float = 0.toFloat()
    @SerializedName("message")
    var message: String? = null
    @SerializedName("cnt")
    var cnt: Int? = 0
    @SerializedName("list")
    var list = ArrayList<ForecastItem>()
    @SerializedName("city")
    var city: City? = null
}
class ForecastItem{
    @SerializedName("dt")
    var dt: Int = 0
    @SerializedName("main")
    var main: MainForecast? = null
    @SerializedName("weather")
    var weather = ArrayList<WeatherForecast>()
    @SerializedName("clouds")
    var clouds: Clouds?=null
    @SerializedName("wind")
    var wind: WindForecast?=null
    @SerializedName("visibilty")
    var visibilityForecast: Float= 0.toFloat()
    @SerializedName("pop")
    var pop: Float= 0.toFloat()
    @SerializedName("rain")
    var rain: Rain? = null
    @SerializedName("sys")
    var sys: SysForecast? = null
    @SerializedName("dt_txt")
    var dt_txt: String? = null

}
class City {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name : String? = null
    @SerializedName("coord")
    var coord : Coord? = null
    @SerializedName("country")
    var country: String?= null
    @SerializedName("timezone")
    var tz : Int = 0
    @SerializedName("sunrise")
    var sunrise: Int = 0;
    @SerializedName("sunset")
    var sunset: Int= 0;
}

class WindForecast {
    @SerializedName("speed")
    var speed: Float = 0.toFloat()
    @SerializedName("deg")
    var deg: Float = 0.toFloat()
    @SerializedName("gust")
    var gust: Float = 0.toFloat()
}

class MainForecast {
    @SerializedName("temp")
    var temp: Float = 0f
    @SerializedName("feels_like")
    var feels_like: Float = 0f
    @SerializedName("temp_min")
    var temp_min: Float = 0f
    @SerializedName("temp_max")
    var temp_max : Float = 0f
    @SerializedName("pressure")
    var pressure : Float = 0f
    @SerializedName("sea_level")
    var sea_level : Float = 0f
    @SerializedName("grnd_level")
    var grnd_level : Float = 0f
    @SerializedName("humidity")
    var humidity : Float = 0f
    @SerializedName("temp_kf")
    var temp_kf : Float = 0f
}
class SysForecast {
    @SerializedName("pod")
    var pod: String?= null
}
class WeatherForecast{
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("main")
    var main: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("icon")
    var icon: String? = null
}
