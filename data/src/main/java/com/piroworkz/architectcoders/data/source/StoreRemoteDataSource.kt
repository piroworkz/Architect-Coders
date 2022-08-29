package com.piroworkz.architectcoders.data.source

import com.piroworkz.architectcoders.domain.Banner
import com.piroworkz.architectcoders.domain.Product

interface StoreRemoteDataSource {
    enum class Store(val path: String){
        BANNERS("Banners"),
        PRODUCTS("Products")
    }
    suspend fun getProducts(): List<Product>?

    suspend fun getBanners(): List<Banner>?
}