package com.example.mymeteo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymeteo.MainActivity
import com.example.mymeteo.R
import com.example.mymeteo.api.ForecastResponse
import com.example.mymeteo.api.ForecastService
import com.example.mymeteo.api.WeatherResponse
import com.example.mymeteo.api.WeatherService
import com.example.mymeteo.ui.pleinecran.AffichagePleinEcran
import com.example.mymeteo.ui.view.ItemWeatherAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mRecyclerView : RecyclerView
    private lateinit var mAdapter : ListAdapter
    private lateinit var mView :View;
    internal fun getCurrentData(view :View){
        var retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(
                GsonConverterFactory.create()).build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(DefaultCity,ApiKey,Metric,"fr")
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>){
                if(response.code()==200){
                    val weatherResponse = response.body()!!
                    listCityResp.add(weatherResponse)
                    notifyInserted(view)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            }
        })
    }
    internal fun addData(view : View, name: String){
        var retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(
                GsonConverterFactory.create()).build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(name,ApiKey,Metric,"fr")
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>){
                if(response.code()==200){
                    val weatherResponse = response.body()!!
                    listCityResp.add(weatherResponse)
                    notifyInserted(view)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? =(inflater.inflate (R.layout.fragment_home,container,false))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView=view
        val main:MainActivity = super.getContext() as MainActivity
        val fab = main.returnButton()
        fab.visibility=0;
        fab.setOnClickListener{
            addLocationListner(view)
        }
        mRecyclerView = view.findViewById(R.id.weather_recycle_view)
        testrecyclerView=mRecyclerView
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ItemWeatherAdapter(listCityResp,this@HomeFragment)
        }
        if(listCityResp.size==0)getCurrentData(view)
    }
    fun notifyInserted(view :View){
        mRecyclerView = view.findViewById(R.id.weather_recycle_view)
        val pos = mRecyclerView.adapter?.itemCount
        mRecyclerView.adapter?.notifyItemInserted(pos!!)
    }
    fun addLocationListner(view: View){
        val txtSerach: EditText = view.findViewById(R.id.text_search_home)
        addData(view,txtSerach.text.toString())
    }
    companion object  {
        var BaseUrl = "http://api.openweathermap.org/"
        var ApiKey = "9387d7732a59e17de90e4c91d32b1936"
        var DefaultCity = "Marseille"
        var Metric = "metric"
        var listCityResp : MutableList<WeatherResponse> = mutableListOf()
        lateinit var  testrecyclerView: RecyclerView
        var listCityDisplayed : MutableList<String> = mutableListOf()
        var listCityFav : MutableList<String> = mutableListOf()
        var listForecastResponse: MutableList<ForecastResponse> = mutableListOf()
        var selectedElement : String?= null
    }
}