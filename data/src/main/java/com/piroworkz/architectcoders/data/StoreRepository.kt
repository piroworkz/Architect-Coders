package com.piroworkz.architectcoders.data

import com.piroworkz.architectcoders.data.source.StoreLocalDataSource
import com.piroworkz.architectcoders.data.source.StoreRemoteDataSource
import com.piroworkz.architectcoders.domain.Banner
import com.piroworkz.architectcoders.domain.CartItem
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepository @Inject constructor(
    private val remote: StoreRemoteDataSource,
    private val local: StoreLocalDataSource
) {
    val products: Flow<List<Product>> = local.products
    val banners: Flow<List<Banner>> = local.banners
    val shoppingCart: Flow<List<CartItem>> = local.shoppingCart

    suspend fun countProducts(): Int? = local.countProducts()

    fun getProductById(model: String): Flow<Product> = local.getProductById(model)

    suspend fun requestProducts(): Error? =
        local.saveProducts(remote.getProducts() as List<Product>)

    suspend fun requestBanners(): Error? =
        local.saveBanners(remote.getBanners() as List<Banner>)

    suspend fun addToShoppingCart(cartItem: CartItem): Error? =
        if (local.cartItemExists(cartItem.model)) {
            local.updateShoppingCart(cartItem)
        } else {
            local.addToShoppingCart(cartItem)
        }

    suspend fun emptyShoppingCart(shoppingCart: List<CartItem>): Error? =
        local.emptyShoppingCart(shoppingCart)

    suspend fun removeFromShoppingCart(cartItem: CartItem): Error? =
        local.removeFromShoppingCart(cartItem)

    suspend fun switchFavorite(product: Product): Error? {
        val updatedProduct = product.copy(favorite = !product.favorite)
        return local.switchFavorite(updatedProduct)
    }

    suspend fun emptyFavorites(): Error? = local.emptyFavorites()
}