package com.piroworkz.architectcoders.domain

data class PaymentMethod(
    val userId: String,
    val card_holder: String,
    val card_number: String,
    val card_issuer: String,
    val card_expiration: String,
    val card_cvc: String
)