package com.example.mymeteo.ui.view

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeteo.api.WeatherResponse

class ItemWeatherAdapter (private val list: List<WeatherResponse>,private val frag: Fragment): RecyclerView.Adapter<ItemWeatherHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemWeatherHolder {
       var inflater = LayoutInflater.from(parent.context)
        return ItemWeatherHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: ItemWeatherHolder, position: Int) {
       val WeRes: WeatherResponse = list[position]
        holder.bind(WeRes,frag)
    }

    override fun getItemCount(): Int {
       return list.size
    }

}