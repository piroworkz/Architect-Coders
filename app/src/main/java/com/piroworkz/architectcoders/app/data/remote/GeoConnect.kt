package com.piroworkz.architectcoders.app.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object GeoConnect {
    private const val BASE_URL: String = "http://api.geonames.org/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val service: GeoService = retrofit.create()
}