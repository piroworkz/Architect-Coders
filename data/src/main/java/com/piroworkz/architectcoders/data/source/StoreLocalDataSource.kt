package com.piroworkz.architectcoders.data.source

import com.piroworkz.architectcoders.domain.*
import kotlinx.coroutines.flow.Flow

interface StoreLocalDataSource {

    val banners: Flow<List<Banner>>
    val products: Flow<List<Product>>
    fun getProductById(model: String): Flow<Product>
    val shoppingCart: Flow<List<CartItem>>

    suspend fun countProducts(): Int?

    suspend fun saveProducts(products: List<Product>): Error?

    suspend fun saveBanners(banners: List<Banner>): Error?

    suspend fun addToShoppingCart(cartItem: CartItem): Error?

    suspend fun updateShoppingCart(cartItem: CartItem): Error?

    suspend fun updateProducts(cartItem: CartItem): Error?

    suspend fun resetProduct(cartItem: CartItem): Error?

    suspend fun cartItemExists(model: String): Boolean

    suspend fun removeFromShoppingCart(cartItem: CartItem): Error?

    suspend fun emptyShoppingCart(shoppingCart: List<CartItem>): Error?

    suspend fun switchFavorite(product: Product): Error?

    suspend fun emptyFavorites(): Error?
}