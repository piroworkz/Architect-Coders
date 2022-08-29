package com.piroworkz.architectcoders.app.ui.detail.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.architectcoders.app.di.DetailFragmentProductModel
import com.piroworkz.architectcoders.app.ui.common.toError
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.Product
import com.piroworkz.architectcoders.usecases.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @DetailFragmentProductModel
    private val model: String,
    private val getProductByIdUseCase: GetProductByIdUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        getProductById()
    }

    private fun getProductById() {
        launchDataLoad {
            getProductByIdUseCase(model).collect { product ->
                _state.update { it.copy(product = product) }
            }
        }
    }


    private fun launchDataLoad(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.toError()) }
            }
        }
    }

    data class State(
        val product: Product? = null,
        val error: Error? = null
    )
}