package com.piroworkz.architectcoders.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payment_method")
data class DbPaymentMethod(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: String,
    val card_holder: String,
    val card_number: String,
    val card_issuer: String,
    val card_expiration: String,
    val card_cvc: String
)