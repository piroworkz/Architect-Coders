package com.piroworkz.architectcoders.app.data

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.piroworkz.architectcoders.data.source.LocationDataSource
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class GMSLocationDataSource @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient,
    private val geocoder: Geocoder
) : LocationDataSource {


    @SuppressLint("MissingPermission")
    override suspend fun findLastRegion(): String? {
        return suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnCompleteListener {
                    continuation.resume(it.result.toRegion())
                }
        }
    }

    private fun Location?.toRegion(): String? {
        val address = this?.let {
            geocoder.getFromLocation(latitude, longitude, 1)
        }
        return address?.firstOrNull()?.countryCode
    }
}