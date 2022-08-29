package com.piroworkz.architectcoders.app.ui.shopping_cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.architectcoders.app.ui.common.toError
import com.piroworkz.architectcoders.domain.CartItem
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.usecases.EmptyShoppingCartUseCase
import com.piroworkz.architectcoders.usecases.GetShoppingCartUseCase
import com.piroworkz.architectcoders.usecases.RemoveFromShoppingCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val getShoppingCartUseCase: GetShoppingCartUseCase,
    private val removeFromShoppingCartUseCase: RemoveFromShoppingCartUseCase,
    private val emptyShoppingCartUseCase: EmptyShoppingCartUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getShoppingCart()
    }

    fun removeFromShoppingCart(cartItem: CartItem) {
        launchData { removeFromShoppingCartUseCase(cartItem) }
    }

    fun emptyShoppingCart() {
        launchData {
            _state.value.shoppingCart?.let { emptyShoppingCartUseCase(it) }
        }
    }

    private fun getShoppingCart() {
        launchData {
            getShoppingCartUseCase().collect { list -> _state.update { it.copy(shoppingCart = list) } }
        }
    }

    data class State(
        val shoppingCart: List<CartItem>? = null,
        val purchaseTotal: Int? = null,
        val error: Error? = null
    )


    fun getPurchaseTotal(list: List<CartItem>?) =
        list?.let { cartItems ->
            _state.update { state ->
                state.copy(
                    purchaseTotal =
                    cartItems.sumOf { (it.largeSize + it.mediumSize + it.smallSize) * it.price }
                )
            }
        }

    private fun launchData(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.toError()) }
            }
        }
    }

}