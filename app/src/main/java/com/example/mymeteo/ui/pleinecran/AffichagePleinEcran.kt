package com.example.mymeteo.ui.pleinecran

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeteo.R
import com.example.mymeteo.api.ForecastItem
import com.example.mymeteo.api.ForecastResponse
import com.example.mymeteo.api.ForecastService
import com.example.mymeteo.ui.home.HomeFragment
import com.example.mymeteo.ui.view.ItemWeatherForecastAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AffichagePleinEcran : Fragment() {

    companion object {
        fun newInstance() = AffichagePleinEcran()
        var forecastResponse:  ForecastResponse ?=null
    }

    private lateinit var viewModel: AffichagePleinEcranViewModel
    private lateinit var mRecyclerView : RecyclerView
    private  var mList : MutableList<ForecastItem> = mutableListOf()
    internal fun testForecast(name:String){

        var retrofit = Retrofit.Builder().baseUrl(HomeFragment.BaseUrl).addConverterFactory(
                GsonConverterFactory.create()).build()
        val service = retrofit.create(ForecastService::class.java)
        val call = service.getForecast(name, HomeFragment.ApiKey, HomeFragment.Metric,"fr")
        call.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {
                if(response.code()==200){
                    forecastResponse=response.body()!!
                    display()
                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.e("test","pkçamarchepas")
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.affichage_plein_ecran_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AffichagePleinEcranViewModel::class.java)
        testForecast(HomeFragment.selectedElement!!)
        mRecyclerView = view?.findViewById<RecyclerView>(R.id.forecast_fullscreen_recyclerforecast)!!
        mRecyclerView.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = ItemWeatherForecastAdapter(mList)
        }

    }
    fun display(){
        val viewTitle = view?.findViewById<TextView>(R.id.forecast_fullscreen_title)
        val viewCountry= view?.findViewById<TextView>(R.id.forecast_fullscreen_country)
        val viewTemp = view?.findViewById<TextView>(R.id.forecast_fullscreen_temperature)
        val viewIcon = view?.findViewById<ImageView>(R.id.forecast_fullscreen_icon)
        viewTitle?.text= forecastResponse?.city?.name
        viewTemp?.text = forecastResponse?.list!![0].main?.temp.toString()
        viewCountry?.text= forecastResponse?.city?.country
        when(forecastResponse?.list!![0].weather[0].icon){
            "01d" -> viewIcon?.setImageResource(R.drawable.sun)
            "02d" -> viewIcon?.setImageResource(R.drawable.sun_cloud)
            "03d" -> viewIcon?.setImageResource(R.drawable.cloud)
            "04d" -> viewIcon?.setImageResource(R.drawable.cloud)
            "09d" -> viewIcon?.setImageResource(R.drawable.cloud_rain_single)
            "10d" -> viewIcon?.setImageResource(R.drawable.cloud_rain)
            "11d" -> viewIcon?.setImageResource(R.drawable.lightning)
            "13d" -> viewIcon?.setImageResource(R.drawable.snowflake)
            "50d" -> viewIcon?.setImageResource(R.drawable.fog)
            "01n" -> viewIcon?.setImageResource(R.drawable.moon)
            "02n" -> viewIcon?.setImageResource(R.drawable.moon_cloud)
            "03n" -> viewIcon?.setImageResource(R.drawable.cloud)
            "04n" -> viewIcon?.setImageResource(R.drawable.cloud)
            "09n" -> viewIcon?.setImageResource(R.drawable.cloud_rain_single)
            "10n" -> viewIcon?.setImageResource(R.drawable.cloud_rain)
            "11n" -> viewIcon?.setImageResource(R.drawable.lightning)
            "13n" -> viewIcon?.setImageResource(R.drawable.snowflake)
            "50n" -> viewIcon?.setImageResource(R.drawable.fog)
        }
        mList.addAll( forecastResponse?.list!!)
        mRecyclerView.adapter?.notifyDataSetChanged()
    }


}