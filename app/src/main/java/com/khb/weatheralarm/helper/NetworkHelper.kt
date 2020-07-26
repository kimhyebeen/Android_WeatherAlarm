package com.khb.weatheralarm.helper

import android.content.Context
import com.khb.weatheralarm.R
import com.khb.weatheralarm.model.WeatherAPI
import com.khb.weatheralarm.model.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkHelper(context: Context) {
    val context: Context

    init {
        this.context = context
    }

    fun requestHourlyWeatherAPI(lat: String, lon: String): WeatherModel? {
        var weatherModel: WeatherModel? = null

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(WeatherAPI::class.java).let {
            it.getWeatherList(lat, lon, "{current,minutely,daily}", context.getString(R.string.api_key), "metric")
        }?.enqueue(object : Callback<WeatherModel> {
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                println("1 실패 : $t")
            }

            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                println("1 성공 : ${response.body().toString()}")
                weatherModel = response.body()
            }
        })
        return weatherModel
    }

    fun requestCurrentWeatherAPI(lat: String, lon: String): Call<WeatherModel>? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(WeatherAPI::class.java)
        val getWeather = api.getWeatherList(lat, lon, "{hourly,minutely,daily}", context.getString(R.string.api_key), "metric")

        return getWeather
    }

    fun requestDailyWeatherAPI(lat: String, lon: String): Call<WeatherModel>? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(WeatherAPI::class.java)
        val getWeather = api.getWeatherList(lat, lon, "{hourly,minutely,current}", context.getString(R.string.api_key), "metric")

        return getWeather
    }
}