package com.piroworkz.architectcoders.domain

data class CartItem(
    val model: String,
    val description: String,
    val price: Int,
    val title: String,
    val imageUrl: String,
    val smallSize: Int,
    val mediumSize: Int,
    val largeSize: Int
)