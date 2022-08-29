package com.piroworkz.architectcoders.data.remote.datasource

import com.piroworkz.architectcoders.data.source.StoreRemoteDataSource
import com.piroworkz.architectcoders.domain.Banner
import com.piroworkz.architectcoders.domain.Product
import com.piroworkz.architectcoders.testshared.bannerList
import com.piroworkz.architectcoders.testshared.productListOf
import javax.inject.Inject

class FakeStoreFireStoreDataSource @Inject constructor() : StoreRemoteDataSource {
    private val products: List<Product> = productListOf
    private val banners: List<Banner> = bannerList

    override suspend fun getProducts(): List<Product>? {
        println("CALLED FakeFirestore:getProducts")
        return products
    }

    override suspend fun getBanners(): List<Banner>? {
        println("CALLED FakeFirestore:getBanners")
        return banners
    }
}