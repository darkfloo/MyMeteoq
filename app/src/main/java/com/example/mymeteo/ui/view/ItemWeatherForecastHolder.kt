package com.example.mymeteo.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeteo.R
import com.example.mymeteo.api.ForecastItem
import com.example.mymeteo.api.ForecastResponse
import com.example.mymeteo.api.WeatherForecast

class ItemWeatherForecastHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.forecast_item,
        parent,
        false)) {
    private var mTemp : TextView? = null
    private var mTempFelt : TextView? = null
    private var mPressure : TextView?= null
    private var mIcon : ImageView?= null
    private var mCompass : ImageView? = null
    private var mHeure: ImageView?= null
    init{
        mTemp = itemView.findViewById(R.id.forecast_item_temp)
        mTempFelt = itemView.findViewById(R.id.forecast_item_tempfelt)
        mPressure = itemView.findViewById(R.id.forecast_item_pressure)
        mIcon = itemView.findViewById(R.id.forecast_item_icon)
        mCompass = itemView.findViewById(R.id.forecast_item_compass)
        mHeure = itemView.findViewById(R.id.forecast_item_heure)
    }
    fun bind(fe : ForecastItem){
        this.mTemp?.text = fe.main?.temp.toString()
        this.mTempFelt?.text = fe.main?.feels_like.toString()
        this.mPressure?.text = fe.main?.pressure.toString()
        when(fe.weather[0].icon){
            "01d" -> mIcon?.setImageResource(R.drawable.sun)
            "02d" -> mIcon?.setImageResource(R.drawable.sun_cloud)
            "03d" -> mIcon?.setImageResource(R.drawable.cloud)
            "04d" -> mIcon?.setImageResource(R.drawable.cloud)
            "09d" -> mIcon?.setImageResource(R.drawable.cloud_rain_single)
            "10d" -> mIcon?.setImageResource(R.drawable.cloud_rain)
            "11d" -> mIcon?.setImageResource(R.drawable.lightning)
            "13d" -> mIcon?.setImageResource(R.drawable.snowflake)
            "50d" -> mIcon?.setImageResource(R.drawable.fog)
            "01n" -> mIcon?.setImageResource(R.drawable.moon)
            "02n" -> mIcon?.setImageResource(R.drawable.moon_cloud)
            "03n" -> mIcon?.setImageResource(R.drawable.cloud)
            "04n" -> mIcon?.setImageResource(R.drawable.cloud)
            "09n" -> mIcon?.setImageResource(R.drawable.cloud_rain_single)
            "10n" -> mIcon?.setImageResource(R.drawable.cloud_rain)
            "11n" -> mIcon?.setImageResource(R.drawable.lightning)
            "13n" -> mIcon?.setImageResource(R.drawable.snowflake)
            "50n" -> mIcon?.setImageResource(R.drawable.fog)
        }

        if(fe.wind?.deg!! > 0 && fe.wind?.deg!! < 45){
            mCompass?.setImageResource(R.drawable.compass_north_east)
        }else if (fe.wind?.deg!! > 45 && fe.wind?.deg!! < 90){
            mCompass?.setImageResource(R.drawable.compass_east)
        }else if(fe.wind?.deg!! > 90 && fe.wind?.deg!! < 135 ){
            mCompass?.setImageResource(R.drawable.compass_south_east)
        }else if(fe.wind?.deg!! > 135 && fe.wind?.deg!! < 180){
            mCompass?.setImageResource(R.drawable.compass_south)
        }else if(fe.wind?.deg!! > 180 && fe.wind?.deg!! < 225){
            mCompass?.setImageResource(R.drawable.compass_south_west)
        }else if(fe.wind?.deg!! > 225 && fe.wind?.deg!! < 270){
            mCompass?.setImageResource(R.drawable.compass_west)
        }else if(fe.wind?.deg!! > 270 && fe.wind?.deg!! < 315){
            mCompass?.setImageResource(R.drawable.compass_north_west)
        }else{
            mCompass?.setImageResource(R.drawable.compass_north)
        }

    }

}