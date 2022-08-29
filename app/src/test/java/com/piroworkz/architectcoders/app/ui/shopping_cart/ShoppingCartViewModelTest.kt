package com.piroworkz.architectcoders.app.ui.shopping_cart

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.ui.shopping_cart.ShoppingCartViewModel.State
import com.piroworkz.architectcoders.testshared.*
import com.piroworkz.architectcoders.usecases.EmptyShoppingCartUseCase
import com.piroworkz.architectcoders.usecases.GetShoppingCartUseCase
import com.piroworkz.architectcoders.usecases.RemoveFromShoppingCartUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ShoppingCartViewModelTest {

    @get:Rule
    val rule = CoroutinesTestRule()

    @Mock
    lateinit var getShoppingCartUseCase: GetShoppingCartUseCase

    @Mock
    lateinit var removeFromShoppingCartUseCase: RemoveFromShoppingCartUseCase

    @Mock
    lateinit var emptyShoppingCartUseCase: EmptyShoppingCartUseCase

    private lateinit var viewModel: ShoppingCartViewModel

    private val state = State().copy(
        shoppingCart = cartItemList
    )

    @Before
    fun setUp(): Unit = runTest {
        whenever(getShoppingCartUseCase()).thenReturn(flowOf(cartItemList))
        whenever(removeFromShoppingCartUseCase(cartItem)).thenReturn(null)
        whenever(emptyShoppingCartUseCase(cartItemList)).thenReturn(null)

        viewModel = ShoppingCartViewModel(
            getShoppingCartUseCase,
            removeFromShoppingCartUseCase,
            emptyShoppingCartUseCase
        )
    }


    @Test
    fun `update state with a list of cart items`(): Unit = runTest {
        viewModel.state.test {
            assertThat(state.shoppingCart).isEqualTo(awaitItem().shoppingCart)
        }
    }

    @Test
    fun `update state with the purchase total for shopping cart`(): Unit = runTest {
        val actualState = state.copy(purchaseTotal = 900)
        viewModel.getPurchaseTotal(cartItemList)
        viewModel.state.test {
            assertThat(actualState.purchaseTotal).isEqualTo(awaitItem().purchaseTotal)
        }
    }

    @Test
    fun `calls get shopping cart use case`() {
        verify(getShoppingCartUseCase).invoke()
    }

    @Test
    fun `calls remove from shopping cart use case`(): Unit = runTest {
        viewModel.removeFromShoppingCart(cartItem)
        runCurrent()
        verify(removeFromShoppingCartUseCase).invoke(cartItem)
    }

    @Test
    fun `calls empty shopping cart use case`(): Unit = runTest {
        viewModel.emptyShoppingCart()
        runCurrent()
        verify(emptyShoppingCartUseCase).invoke(cartItemList)
    }

}