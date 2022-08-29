package com.piroworkz.architectcoders.app.ui.detail.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.architectcoders.app.di.DetailDialogProductModel
import com.piroworkz.architectcoders.app.ui.common.toError
import com.piroworkz.architectcoders.domain.CartItem
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.Product
import com.piroworkz.architectcoders.domain.Size
import com.piroworkz.architectcoders.domain.Size.*
import com.piroworkz.architectcoders.usecases.AddToCartUseCase
import com.piroworkz.architectcoders.usecases.GetProductByIdUseCase
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailDialogViewModel @Inject constructor(
    @DetailDialogProductModel
    private val model: String?,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        getUserId()
        getProductById()
    }

    fun onSizeChipSelected(size: Size) {
        _state.update { it.copy(size = size) }
    }

    fun resetSize() {
        _state.update { it.copy(size = null) }
    }

    fun addToPieces() {
        _state.update {
            it.copy(
                pieces = if (it.pieces < getInventory(it)!!) it.pieces + 1 else it.pieces
            )
        }
    }

    fun subtractFromPieces() {
        _state.update { it.copy(pieces = if (it.pieces == 0) 0 else it.pieces.minus(1)) }
    }

    fun addToCart() {
        launchDataLoad {
            val error = addToCartUseCase(_state.toCartItem())
            if (error == null) {
                _state.update {
                    it.copy(
                        toastMessage = _state.value.product?.title,
                        dismissDialog = true
                    )
                }
            } else {
                _state.update { it.copy(error = error) }
            }
        }
    }

    fun dismissDialog() {
        _state.update { it.copy(dismissDialog = true) }
    }

    fun resetToast() {
        _state.update { it.copy(toastMessage = null) }
    }


    data class State(
        val userId: String? = null,
        val product: Product? = null,
        val size: Size? = null,
        val pieces: Int = 0,
        val toastMessage: String? = null,
        val dismissDialog: Boolean = false,
        val error: Error? = null,
    )

    private fun getInventory(state: State): Int? =
        when (state.size) {
            SMALL -> state.product?.smallSize
            MEDIUM -> state.product?.mediumSize
            LARGE -> state.product?.largeSize
            else -> null
        }

    private fun getUserId() {
        launchDataLoad {
            _state.update { it.copy(userId = getUserIdUseCase() ?: throw IOException()) }
        }
    }

    private fun getProductById() {
        launchDataLoad {
            getProductByIdUseCase(model!!)
                .collect { product: Product -> _state.update { it.copy(product = product) } }
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.toError()) }
            } finally {
            }
        }
    }

    private fun MutableStateFlow<State>.toCartItem(): CartItem = with(value) {
        CartItem(
            model = product!!.model,
            description = product.description,
            price = product.price,
            title = product.title,
            imageUrl = product.imageUrl,
            largeSize = if (size == LARGE) pieces else 0,
            mediumSize = if (size == MEDIUM) pieces else 0,
            smallSize = if (size == SMALL) pieces else 0
        )
    }
}