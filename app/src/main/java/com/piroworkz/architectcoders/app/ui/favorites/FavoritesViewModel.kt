package com.piroworkz.architectcoders.app.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.architectcoders.app.ui.common.toError
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.Product
import com.piroworkz.architectcoders.usecases.EmptyFavoritesUseCase
import com.piroworkz.architectcoders.usecases.GetProductsUseCase
import com.piroworkz.architectcoders.usecases.SwitchFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val switchFavoritesUseCase: SwitchFavoritesUseCase,
    private val emptyFavoritesUseCase: EmptyFavoritesUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state: StateFlow<State> get() = _state.asStateFlow()

    init {
        launchData {
            getProductsUseCase().collect { list -> _state.update { it.copy(favorites = list.filter { productIs -> productIs.favorite }) } }
        }
    }

    fun switchFavorite(product: Product) {
        println("called switchFavorite")
        launchData {
            val error = switchFavoritesUseCase(product)
            _state.update { state ->
                state.copy(error = error)
            }
        }
    }

    fun emptyFavorite() {
        launchData {
            _state.update { state ->
                state.copy(error = emptyFavoritesUseCase())
            }
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

    data class State(
        val favorites: List<Product>? = null,
        val error: Error? = null,
    )
}