package com.piroworkz.architectcoders.app.data.local.database

import com.piroworkz.architectcoders.app.data.local.entities.DbBanner
import com.piroworkz.architectcoders.app.data.local.entities.DbCartItem
import com.piroworkz.architectcoders.app.data.local.entities.DbProduct
import com.piroworkz.architectcoders.app.data.utils.toDatabaseBanner
import com.piroworkz.architectcoders.app.data.utils.toDatabaseProduct
import com.piroworkz.architectcoders.testshared.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FakeStoreDao(
    defaultProducts: MutableList<DbProduct>,
    defaultBanners: MutableList<DbBanner> = bannerList.toDatabaseBanner().toMutableList(),
    defaultShoppingCart: MutableList<DbCartItem> = mutableListOf(),
) : StoreDao {

    private val products: MutableStateFlow<MutableList<DbProduct>> =
        MutableStateFlow(defaultProducts)
    private val banners: MutableStateFlow<MutableList<DbBanner>> =
        MutableStateFlow(defaultBanners)
    private val shoppingCart: MutableStateFlow<MutableList<DbCartItem>> =
        MutableStateFlow(defaultShoppingCart)

    override suspend fun countProducts(): Int {
        println("CALLED FakeStoreDao:countProducts")
        return products.value.size
    }

    suspend fun emit() {
        println("CALLED FakeStoreDao:emit")
        products.emit(products.value)
    }

    override fun getProducts(): Flow<List<DbProduct>> {
        println("CALLED FakeStoreDao:getProducts")
        return products
    }

    override suspend fun saveProducts(dbProducts: List<DbProduct>) {
        println("CALLED FakeStoreDao:saveProducts")
        products.value = dbProducts.toMutableList()
    }

    override fun getProductsById(model: String): Flow<DbProduct> {
        println("CALLED FakeStoreDao:getProductsById")
        return flowOf(products.value.find { it.model == model }!!)
    }

    override suspend fun deleteProducts() {
        println("CALLED FakeStoreDao:deleteProducts")
        products.value = mutableListOf()
    }

    override suspend fun updateProductSizeCount(
        smallSize: Int,
        mediumSize: Int,
        largeSize: Int,
        model: String
    ) {
        println("CALLED FakeStoreDao:updateProductSizeCount")
        val oldProduct = products.value.find { it.model == model }
        val index: Int = products.value.indexOf(oldProduct)
        products.value[index] =
            oldProduct.calculatePieces(smallSize, mediumSize, largeSize, Int::minus)
    }

    override suspend fun resetProductSizeCount(
        smallSize: Int,
        mediumSize: Int,
        largeSize: Int,
        model: String
    ) {
        println("CALLED FakeStoreDao:resetProductSizeCount")
        val oldProduct = products.value.find { it.model == model }
        val index: Int = products.value.indexOf(oldProduct)
        products.value[index] =
            oldProduct.calculatePieces(smallSize, mediumSize, largeSize, Int::plus)
    }

    override fun getBanners(): Flow<List<DbBanner>> {
        println("CALLED FakeStoreDao:getBanners")
        return banners
    }

    override suspend fun saveBanners(dbBanner: List<DbBanner>) {
        println("CALLED FakeStoreDao:saveBanners")
        banners.value = dbBanner.toMutableList()
    }

    override fun getFavorites(): Flow<List<DbProduct>> {
        println("CALLED FakeStoreDao:getFavorites")
        return products.map { it.filter { product: DbProduct -> product.favorite } }
    }

    override suspend fun emptyFavorites() {
        println("CALLED-BEFORE FakeStoreDao:emptyFavorites")
        products.value = productListOf.toDatabaseProduct().toMutableList()

    }

    override suspend fun switchFavorite(model: String, favorite: Boolean) {
        println("CALLED-BEFORE FakeStoreDao:switchFavorite()")
        products.value = switchedList.toDatabaseProduct().toMutableList()
    }

    override suspend fun addToShoppingCart(dbCartItem: DbCartItem) {
        println("CALLED FakeStoreDao:addToShoppingCart")
        shoppingCart.value.add(dbCartItem)
    }

    override fun getShoppingCart(): Flow<List<DbCartItem>> {
        println("CALLED FakeStoreDao:getShoppingCart")
        return shoppingCart
    }

    override suspend fun emptyShoppingCart() {
        println("CALLED FakeStoreDao:emptyShoppingCart")
        shoppingCart.value = mutableListOf()
    }

    override suspend fun updateShoppingCartSizeCount(
        smallSize: Int,
        mediumSize: Int,
        largeSize: Int,
        model: String
    ) {
        println("CALLED FakeStoreDao:updateShoppingCartSizeCount")
        val oldProduct = shoppingCart.value.find { it.model == model }
        val index: Int = shoppingCart.value.indexOf(oldProduct)
        shoppingCart.value[index] =
            oldProduct?.calculatePieces(smallSize, mediumSize, largeSize, Int::plus)!!
    }

    override suspend fun removeFromShoppingCart(model: String) {
        println("CALLED FakeStoreDao:removeFromShoppingCart")
        shoppingCart.value = shoppingCart.value
            .dropWhile { it.model == model }.toMutableList()
    }

    override suspend fun cartItemExists(model: String): Boolean {
        println("CALLED FakeStoreDao:cartItemExists")
        return shoppingCart.value.find { it.model == model } != null
    }

}

private fun DbProduct?.calculatePieces(
    smallSize: Int,
    mediumSize: Int,
    largeSize: Int,
    operator: (Int, Int) -> Int
): DbProduct {
    return DbProduct(
        model = this?.model!!,
        description = description,
        price = price,
        title = title,
        imageUrl = imageUrl,
        smallSize = operator(this.smallSize, smallSize),
        mediumSize = operator(this.mediumSize, mediumSize),
        largeSize = operator(this.largeSize, largeSize),
        favorite = favorite
    )
}

private fun DbCartItem.calculatePieces(
    smallSize: Int,
    mediumSize: Int,
    largeSize: Int,
    operator: (Int, Int) -> Int
): DbCartItem {
    return DbCartItem(
        model = model,
        description = description,
        price = price,
        title = title,
        imageUrl = imageUrl,
        smallSize = operator(this.smallSize, smallSize),
        mediumSize = operator(this.mediumSize, mediumSize),
        largeSize = operator(this.largeSize, largeSize),
    )
}