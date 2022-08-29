package com.piroworkz.architectcoders.app.data.remote.model

data class RemotePostalAddress(
    val adminCode1: String,
    val adminCode2: String,
    val adminName1: String,
    val adminName2: String,
    val countryCode: String,
    val lat: Double,
    val lng: Double,
    val placeName: String,
    val postalCode: String
)