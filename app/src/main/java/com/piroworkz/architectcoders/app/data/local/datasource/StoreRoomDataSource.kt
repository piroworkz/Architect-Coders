package com.piroworkz.architectcoders.app.data.local.datasource

import com.piroworkz.architectcoders.app.data.local.database.StoreDao
import com.piroworkz.architectcoders.app.data.utils.toDatabaseBanner
import com.piroworkz.architectcoders.app.data.utils.toDatabaseProduct
import com.piroworkz.architectcoders.app.data.utils.toDbCartItem
import com.piroworkz.architectcoders.app.data.utils.toDomain
import com.piroworkz.architectcoders.app.ui.common.tryCatch
import com.piroworkz.architectcoders.data.source.StoreLocalDataSource
import com.piroworkz.architectcoders.domain.Banner
import com.piroworkz.architectcoders.domain.CartItem
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreRoomDataSource @Inject constructor(private val storeDao: StoreDao) :
    StoreLocalDataSource {

    override val banners = storeDao.getBanners().map { it.map { banner -> banner.toDomain() } }

    override val products: Flow<List<Product>> =
        storeDao.getProducts().map { it.map { product -> product.toDomain() } }

    override fun getProductById(model: String): Flow<Product> =
        storeDao.getProductsById(model).map { it.toDomain() }

    override val shoppingCart =
        storeDao.getShoppingCart().map { it.map { cartItem -> cartItem.toDomain() } }

    override suspend fun countProducts(): Int? = tryCatch { storeDao.countProducts() }.fold(
        ifLeft = { null },
        ifRight = { it }
    )

    override suspend fun saveProducts(products: List<Product>): Error? = tryCatch {
        storeDao.saveProducts(products.toDatabaseProduct())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun saveBanners(banners: List<Banner>) = tryCatch {
        storeDao.saveBanners(banners.toDatabaseBanner())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )

    override suspend fun addToShoppingCart(cartItem: CartItem): Error? =
        tryCatch {
            storeDao.addToShoppingCart(cartItem.toDbCartItem())
        }.fold(
            ifLeft = { it },
            ifRight = {
                updateProducts(cartItem)
                null
            }
        )

    override suspend fun updateShoppingCart(cartItem: CartItem): Error? =
        with(cartItem) {
            tryCatch {
                storeDao.updateShoppingCartSizeCount(
                    smallSize,
                    mediumSize,
                    largeSize,
                    model
                )
            }
                .fold(
                    ifLeft = { it },
                    ifRight = {
                        updateProducts(cartItem)
                        null
                    }
                )
        }

    override suspend fun updateProducts(cartItem: CartItem): Error? =
        with(cartItem) {
            tryCatch { storeDao.updateProductSizeCount(smallSize, mediumSize, largeSize, model) }
                .fold(
                    ifLeft = { it },
                    ifRight = { null }
                )
        }

    override suspend fun resetProduct(cartItem: CartItem) =
        with(cartItem) {
            tryCatch { storeDao.resetProductSizeCount(smallSize, mediumSize, largeSize, model) }
                .fold(
                    ifLeft = { it },
                    ifRight = { null }
                )
        }

    override suspend fun cartItemExists(model: String): Boolean = storeDao.cartItemExists(model)


    override suspend fun removeFromShoppingCart(cartItem: CartItem): Error? =
        tryCatch {
            resetProduct(cartItem)
            storeDao.removeFromShoppingCart(cartItem.model)
        }
            .fold(
                ifLeft = { it },
                ifRight = { null }
            )


    override suspend fun emptyShoppingCart(shoppingCart: List<CartItem>): Error? =
        tryCatch {
            shoppingCart.forEach { resetProduct(it) }
            storeDao.emptyShoppingCart()
        }
            .fold(
                ifLeft = { it },
                ifRight = { null }
            )

    override suspend fun switchFavorite(product: Product): Error? =
        tryCatch {
            storeDao.switchFavorite(product.model, product.favorite)
        }.fold(
            ifLeft = { it },
            ifRight = { null }
        )

    override suspend fun emptyFavorites(): Error? =
        tryCatch { storeDao.emptyFavorites() }
            .fold(
                ifLeft = { it },
                ifRight = { null }
            )

}