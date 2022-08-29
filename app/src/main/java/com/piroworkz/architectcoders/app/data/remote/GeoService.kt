package com.piroworkz.architectcoders.app.data.remote

import com.piroworkz.architectcoders.app.data.remote.model.GeoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoService {
    @GET("postalCodeSearchJSON")
    suspend fun requestAddressByZipCode(
        @Query(value = "postalcode") postalCode: String,
        @Query(value = "country") countryCode: String,
        @Query(value = "username") userName: String,
    ): GeoResponse
}