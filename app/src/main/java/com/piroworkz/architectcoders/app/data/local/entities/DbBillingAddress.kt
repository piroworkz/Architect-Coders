package com.piroworkz.architectcoders.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "billing_address")
data class DbBillingAddress(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: String,
    val street: String,
    val town: String,
    val city: String,
    val state: String,
    val zipCode: String
)