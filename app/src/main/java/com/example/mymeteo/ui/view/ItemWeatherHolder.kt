package com.example.mymeteo.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeteo.R
import com.example.mymeteo.api.WeatherResponse
import com.example.mymeteo.ui.home.HomeFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mymeteo.ui.pleinecran.AffichagePleinEcran

class ItemWeatherHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
    R.layout.weather_item,parent,false))  {
    private var mTitle: TextView? = null
    private var mCountry:TextView? = null
    private var mTemperature:TextView? = null
    private var mIcon: ImageView? = null
    private var mStar: ImageView ?= null
    private var mFrag: HomeFragment ?=null
    private var mDelete: ImageView ?=null
    init{
        mTitle = itemView.findViewById(R.id.weather_item_title)
        mCountry = itemView.findViewById(R.id.weather_item_country)
        mTemperature = itemView.findViewById(R.id.weather_item_temperature)
        mIcon = itemView.findViewById(R.id.weather_item_icon)
        mStar = itemView.findViewById(R.id.weather_item_fav)
        mDelete = itemView.findViewById(R.id.weather_item_delete)
    }
    fun bind (we : WeatherResponse, frag: Fragment){
       this.mTemperature?.text =  we.main?.temp.toString()
        this.mCountry?.text  = we.sys?.country
        this.mTitle?.text = we.name
        mFrag=frag as HomeFragment
        mDelete?.setOnClickListener{
            callBackDelete()
        }
        mStar?.setOnClickListener{
            callBackFav()
        }
        mIcon?.setOnClickListener{
            navigate()
        }
        checkFav()
        when(we.weather[0].icon){
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

    }
    fun callBackDelete(){
        HomeFragment.listCityResp.removeAt(adapterPosition)
        HomeFragment.testrecyclerView.adapter?.notifyItemRemoved(adapterPosition)
    }
    fun checkFav(){
        if(HomeFragment.listCityFav.contains(mTitle?.text.toString())){
            mStar?.setImageResource(android.R.drawable.star_on)
        }else{
            mStar?.setImageResource(android.R.drawable.star_off)
        }
    }
    fun callBackFav(){
        if(HomeFragment.listCityFav.contains(mTitle?.text.toString())){
            HomeFragment.listCityFav.remove(mTitle?.text.toString())
            mStar?.setImageResource(android.R.drawable.star_off)
            mFrag?.updateList()
        }else{
           HomeFragment.listCityFav.add(mTitle?.text.toString())
           mStar?.setImageResource(android.R.drawable.star_on)
            mFrag?.updateList()
        }

    }
    fun navigate(){
        val nav= mFrag?.findNavController()?.navigate(R.id.nav_test)
        HomeFragment.selectedElement = mTitle?.text.toString()
    }
}