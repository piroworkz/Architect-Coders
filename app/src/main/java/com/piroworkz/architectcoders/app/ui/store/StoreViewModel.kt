package com.piroworkz.architectcoders.app.ui.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.architectcoders.app.ui.common.toError
import com.piroworkz.architectcoders.domain.Banner
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.Product
import com.piroworkz.architectcoders.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val requestProducts: RequestProductsUseCase,
    private val requestBanners: RequestBannersUseCase,
    private val products: GetProductsUseCase,
    private val banners: GetBannersUseCase,
    private val switchFavoriteUseCase: SwitchFavoritesUseCase,
    private val countProductsUseCase: CountProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        launch { products().collect { list -> _state.update { it.copy(products = list) } } }
        launch { banners().collect { list -> _state.update { it.copy(banners = list) } } }
    }

    fun switchFavorite(product: Product) =
        launch { switchFavoriteUseCase(product) }

    fun onPermissionRequestResult() {
        viewModelScope.launch {
            if (countProductsUseCase() == 0) {
                requestProducts()
                requestBanners()
            }
        }
    }

    data class State(
        val products: List<Product>? = null,
        val banners: List<Banner>? = null,
        val error: Error? = null
    )

    private fun launch(body: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                body()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.toError()) }
            }
        }
    }
}