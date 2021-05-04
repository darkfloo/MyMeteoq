package com.example.mymeteo.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeteo.api.ForecastItem
import com.example.mymeteo.api.WeatherForecast

class ItemWeatherForecastAdapter(private val list: List<ForecastItem>): RecyclerView.Adapter<ItemWeatherForecastHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemWeatherForecastHolder {
        var inflater = LayoutInflater.from(parent.context)
        return ItemWeatherForecastHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: ItemWeatherForecastHolder, position: Int) {
        val FeRes :ForecastItem = list[position]
        holder.bind(FeRes)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}