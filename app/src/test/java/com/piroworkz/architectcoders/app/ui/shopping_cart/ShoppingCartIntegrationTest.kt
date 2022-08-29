package com.piroworkz.architectcoders.app.ui.shopping_cart

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.buildStoreRepository
import com.piroworkz.architectcoders.app.ui.shopping_cart.ShoppingCartViewModel.State
import com.piroworkz.architectcoders.domain.CartItem
import com.piroworkz.architectcoders.domain.Product
import com.piroworkz.architectcoders.testshared.*
import com.piroworkz.architectcoders.usecases.EmptyShoppingCartUseCase
import com.piroworkz.architectcoders.usecases.GetShoppingCartUseCase
import com.piroworkz.architectcoders.usecases.RemoveFromShoppingCartUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ShoppingCartIntegrationTest {
    @get:Rule
    val rule = CoroutinesTestRule()


    private val state = State().copy(shoppingCart = cartItemList)

    @Test
    fun `on view model init gets shopping cart from local data source`() = runTest {
        val viewModel = buildViewModel(shoppingCart = cartItemList)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())
        }
    }

    @Test
    fun `removes item from local database shopping cart`() = runTest {
        val viewModel = buildViewModel(shoppingCart = cartItemList)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())

            viewModel.removeFromShoppingCart(cartItemList[0])
            assertThat(awaitItem().shoppingCart).doesNotContain(cartItemList[0])
        }
    }

    @Test
    fun `removes all items from shopping cart`() = runTest {
        val viewModel = buildViewModel(shoppingCart = cartItemList)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())

            viewModel.emptyShoppingCart()
            assertThat(awaitItem().shoppingCart).isEmpty()
        }
    }
}

private fun buildViewModel(
    product: List<Product> = emptyList(),
    shoppingCart: List<CartItem> = emptyList()
): ShoppingCartViewModel {
    val repository = buildStoreRepository(product, shoppingCart)

    val getShoppingCartUseCase = GetShoppingCartUseCase(repository)
    val removeFromShoppingCartUseCase = RemoveFromShoppingCartUseCase(repository)
    val emptyShoppingCartUseCase = EmptyShoppingCartUseCase(repository)

    return ShoppingCartViewModel(
        getShoppingCartUseCase,
        removeFromShoppingCartUseCase,
        emptyShoppingCartUseCase
    )
}