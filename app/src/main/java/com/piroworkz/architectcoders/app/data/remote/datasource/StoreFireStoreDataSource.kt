package com.piroworkz.architectcoders.app.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.piroworkz.architectcoders.app.data.utils.toBanner
import com.piroworkz.architectcoders.app.data.utils.toProduct
import com.piroworkz.architectcoders.data.source.StoreRemoteDataSource
import com.piroworkz.architectcoders.app.data.remote.getCollection
import com.piroworkz.architectcoders.data.source.StoreRemoteDataSource.Store.BANNERS
import com.piroworkz.architectcoders.data.source.StoreRemoteDataSource.Store.PRODUCTS
import com.piroworkz.architectcoders.domain.Banner
import com.piroworkz.architectcoders.domain.Product
import javax.inject.Inject

class StoreFireStoreDataSource @Inject constructor(private val firestore: FirebaseFirestore) : StoreRemoteDataSource {

    override suspend fun getProducts(): List<Product>? =
        firestore.getCollection(PRODUCTS.path)?.map(QueryDocumentSnapshot::toProduct)

    override suspend fun getBanners(): List<Banner>? =
        firestore.getCollection(BANNERS.path)?.map(QueryDocumentSnapshot::toBanner)
}