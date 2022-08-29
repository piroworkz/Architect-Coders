package com.piroworkz.architectcoders.data

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.data.source.StoreLocalDataSource
import com.piroworkz.architectcoders.data.source.StoreRemoteDataSource
import com.piroworkz.architectcoders.testshared.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StoreRepositoryTest {
    @Mock
    lateinit var remote: StoreRemoteDataSource

    @Mock
    lateinit var local: StoreLocalDataSource

    private lateinit var repository: StoreRepository

    private val productsFlow = flowOf(productListOf)
    private val bannerFlow = flowOf(bannerList)
    private val shoppingCartFlow = flowOf(cartItemList)


    @Before
    fun setUp() {
        whenever(local.products).thenReturn(productsFlow)
        whenever(local.banners).thenReturn(bannerFlow)
        whenever(local.shoppingCart).thenReturn(shoppingCartFlow)
        repository = StoreRepository(remote, local)
    }


    @Test
    fun `returns detail product filtered by id as a flow`() {
        val productFlow = flowOf(product)
        whenever(local.getProductById(any())).thenReturn(productFlow)

        assertThat(repository.getProductById("")).isEqualTo(productFlow)
    }

    @Test
    fun `returns store's products list as a flow`() {
        assertThat(repository.products).isEqualTo(productsFlow)
    }

    @Test
    fun `returns store's banners list as a flow`() {
        assertThat(repository.banners).isEqualTo(bannerFlow)
    }

    @Test
    fun `returns user's shoppingCart list as a flow`() {
        assertThat(repository.shoppingCart).isEqualTo(shoppingCartFlow)
    }

    @Test
    fun `saves products to local database`(): Unit = runTest {
        val products = productListOf
        whenever(remote.getProducts()).thenReturn(products)

        repository.requestProducts()

        verify(local).saveProducts(products)
    }


    @Test
    fun `saves banners to local database`(): Unit = runTest {
        val remoteList = bannerList
        whenever(remote.getBanners()).thenReturn(remoteList)

        repository.requestBanners()

        verify(local).saveBanners(remoteList)
    }


    @Test
    fun `update shopping cart item if it exists`(): Unit = runTest {
        val cartItem = cartItem
        whenever(local.cartItemExists(any())).thenReturn(true)

        repository.addToShoppingCart(cartItem)

        verify(local).updateShoppingCart(any())
    }

    @Test
    fun `add new item to shopping cart if it doesn't exist`(): Unit = runTest {
        val cartItem = cartItem
        whenever(local.cartItemExists(any())).thenReturn(false)

        repository.addToShoppingCart(cartItem)

        verify(local).addToShoppingCart(cartItem)
    }

    @Test
    fun `deletes all items from shopping cart`(): Unit = runTest {
        val cartList = cartItemList

        repository.emptyShoppingCart(cartList)

        verify(local).emptyShoppingCart(cartList)
    }

    @Test
    fun `deletes cartItem from shopping cart`(): Unit = runTest {
        val cartItem = cartItem

        repository.removeFromShoppingCart(cartItem)

        verify(local).removeFromShoppingCart(cartItem)
    }

    @Test
    fun `switches favorite value on product`(): Unit = runTest {
        val product = product

        repository.switchFavorite(product)

        verify(local).switchFavorite(any())
    }

    @Test
    fun `switches all products favorite back to false`(): Unit = runTest {

        repository.emptyFavorites()

        verify(local).emptyFavorites()
    }
}