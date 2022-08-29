package com.piroworkz.architectcoders.app.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piroworkz.architectcoders.app.data.local.entities.DbBanner
import com.piroworkz.architectcoders.app.data.local.entities.DbCartItem
import com.piroworkz.architectcoders.app.data.local.entities.DbProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {

    @Query("SELECT COUNT (model) FROM products")
    suspend fun countProducts(): Int

    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<DbProduct>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProducts(dbProducts: List<DbProduct>)

    @Query("SELECT * FROM products WHERE model = :model")
    fun getProductsById(model: String): Flow<DbProduct>

    @Query("DELETE FROM products")
    suspend fun deleteProducts()

    @Query("UPDATE products SET smallSize = smallSize - :smallSize , mediumSize = mediumSize - :mediumSize , largeSize = largeSize - :largeSize WHERE model = :model ")
    suspend fun updateProductSizeCount(
        smallSize: Int,
        mediumSize: Int,
        largeSize: Int,
        model: String
    )

    @Query("UPDATE products SET smallSize = smallSize + :smallSize , mediumSize = mediumSize + :mediumSize , largeSize = largeSize + :largeSize WHERE model = :model ")
    suspend fun resetProductSizeCount(
        smallSize: Int,
        mediumSize: Int,
        largeSize: Int,
        model: String
    )

    @Query("SELECT * FROM banners")
    fun getBanners(): Flow<List<DbBanner>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBanners(dbBanner: List<DbBanner>)

    @Query("SELECT * FROM products WHERE favorite = 1 ")
    fun getFavorites(): Flow<List<DbProduct>>

    @Query("UPDATE products SET favorite = 0")
    suspend fun emptyFavorites()

    @Query("UPDATE products SET favorite = :favorite WHERE model = :model")
    suspend fun switchFavorite(model: String, favorite: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToShoppingCart(dbCartItem: DbCartItem)

    @Query("SELECT * FROM shopping_cart")
    fun getShoppingCart(): Flow<List<DbCartItem>>

    @Query("DELETE FROM shopping_cart")
    suspend fun emptyShoppingCart()

    @Query("UPDATE shopping_cart SET smallSize = :smallSize + smallSize , mediumSize = :mediumSize + mediumSize, largeSize = :largeSize + largeSize  WHERE model = :model ")
    suspend fun updateShoppingCartSizeCount(
        smallSize: Int,
        mediumSize: Int,
        largeSize: Int,
        model: String
    )

    @Query("DELETE FROM shopping_cart WHERE model = :model")
    suspend fun removeFromShoppingCart(model: String)

    @Query("SELECT EXISTS(SELECT * FROM shopping_cart WHERE model = :model)")
    suspend fun cartItemExists(model: String): Boolean

}