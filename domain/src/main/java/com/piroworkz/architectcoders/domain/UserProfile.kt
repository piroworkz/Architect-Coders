package com.piroworkz.architectcoders.domain

data class UserProfile(
    val user: User?,
    val shippingAddress: ShippingAddress?,
    val billingAddress: BillingAddress?,
    val paymentMethod: PaymentMethod?
)