package com.piroworkz.architectcoders.domain

data class ShippingAddress(
    val userId: String,
    val street: String,
    val town: String,
    val city: String,
    val state: String,
    val zipCode: String
)