package com.piroworkz.architectcoders.app.ui.profile.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.architectcoders.app.ui.common.toError
import com.piroworkz.architectcoders.domain.PaymentMethod
import com.piroworkz.architectcoders.domain.TextInputError
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import com.piroworkz.architectcoders.usecases.SavePaymentMethodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentMethodViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val savePaymentMethodUseCase: SavePaymentMethodUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        launchData { _state.update { it.copy(userId = getUserIdUseCase()) } }
    }

    fun setCardHolder(string: String) = _state.update { it.copy(cardHolder = string) }
    fun setCardIssuer(string: String) = _state.update { it.copy(cardIssuer = string) }
    fun setCardExpiration(string: String) = _state.update { it.copy(cardExpiration = string) }
    fun setCardNUmber(string: String) = _state.update { it.copy(cardNumber = string) }
    fun setCardCvc(string: String) = _state.update { it.copy(cardCvc = string) }
    fun clearError() = _state.update { it.copy(error = null) }

    fun saveCard(state: State) {
        launchData {
            if (state.validateForm()) {
                _state.update {
                    it.copy(isSaved = savePaymentMethodUseCase(state.toPaymentMethod()) == null)
                }
            } else {
                _state.update { it.copy(error = TextInputError.IsEmpty) }
            }
        }
    }

    data class State(
        val userId: String? = null,
        val cardHolder: String? = null,
        val cardIssuer: String? = null,
        val cardExpiration: String? = null,
        val cardNumber: String? = null,
        val cardCvc: String? = null,
        val isSaved: Boolean = false,
        val error: TextInputError? = null
    )

    private fun State.validateForm(): Boolean = (userId != null)
            && (cardHolder != null)
            && (cardIssuer != null)
            && (cardExpiration != null)
            && (cardNumber != null)
            && (cardCvc != null)

    private fun State.toPaymentMethod(): PaymentMethod =
        PaymentMethod(
            userId!!, cardHolder!!, cardNumber!!, cardIssuer!!, cardExpiration!!, cardCvc!!
        )

    private fun launchData(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                println(e.toError())
            }
        }
    }
}