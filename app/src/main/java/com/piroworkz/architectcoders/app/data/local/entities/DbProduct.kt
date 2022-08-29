package com.piroworkz.architectcoders.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
data class DbProduct(
    @PrimaryKey(autoGenerate = false)
    val model: String,
    val description: String,
    val price: Int,
    val title: String,
    val imageUrl: String,
    val smallSize: Int,
    val mediumSize: Int,
    val largeSize: Int,
    val favorite: Boolean
)