package com.piroworkz.architectcoders.app.ui.profile.shipping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.architectcoders.app.ui.common.toError
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.PostalAddress
import com.piroworkz.architectcoders.domain.ShippingAddress
import com.piroworkz.architectcoders.domain.TextInputError
import com.piroworkz.architectcoders.domain.TextInputError.ZipCode
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import com.piroworkz.architectcoders.usecases.RequestAddressByZipCodeUseCase
import com.piroworkz.architectcoders.usecases.SaveBillingAddressUseCase
import com.piroworkz.architectcoders.usecases.SaveShippingAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ShippingAddressDialogViewModel @Inject constructor(
    private val saveShippingAddressUseCase: SaveShippingAddressUseCase,
    private val saveBillingAddressUseCase: SaveBillingAddressUseCase,
    private val requestAddressByZipCode: RequestAddressByZipCodeUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    init {
        launch { _state.update { it.copy(userId = getUserIdUseCase()) } }
    }

    fun searchByZipCode(zipCode: String) {
        if (zipCode.length == 5) {
            viewModelScope.launch {
                try {
                    onResultAddress(requestAddressByZipCode(zipCode), zipCode)
                }catch (e: Exception){
                    _state.update { it.copy(error = e.toError()) }
                }finally {
                }
            }
        }
    }

    fun onInputStreet(street: String) = _state.update { state ->
        state.copy(street = street)
    }

    fun onInputTown(town: String) =
        _state.update { state ->
            state.copy(town = town.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            })
        }

    fun trySaveBillingAddressAddress(it: State) {
        if (!it.street.isNullOrBlank() && !it.town.isNullOrBlank()) {
            saveBillingAddress(it)
        } else {
            onEmptyError()
        }
    }

    fun trySaveShippingAddressAddress(it: State) {
        if (!it.street.isNullOrBlank() && !it.town.isNullOrBlank()) {
            saveShippingAddress(it)
        } else {
            onEmptyError()
        }
    }

    fun clearError() = _state.update { it.copy(textInputError = null) }
    fun onChecked() = _state.update { it.copy(useSameAddress = !_state.value.useSameAddress) }

    data class State(
        val userId: String? = null,
        val town: String? = null,
        val street: String? = null,
        val textInputError: TextInputError? = null,
        val townsList: List<String> = listOf(),
        val postalAddress: PostalAddress? = null,
        val isSaved: Boolean = false,
        val useSameAddress: Boolean = false,
        val loading: Boolean = false,
        val error: Error? = null
    )

    private fun onResultAddress(
        address: List<PostalAddress>?,
        zipCode: String
    ) {
        if (address != null) {
            _state.update { state ->
                state.copy(
                    townsList = address.map { it.town },
                    postalAddress = address[0],
                )
            }
        } else {
            _state.update { it.copy(textInputError = ZipCode(error = zipCode)) }
        }

    }

    private fun saveShippingAddress(state: State) {
        if (state.useSameAddress) {
            launch {
                if (
                    saveShippingAddressUseCase(state.toShippingAddress()) == null &&
                    saveBillingAddressUseCase(state.toBillingAddress()) == null
                ) {
                    _state.update { it.copy(isSaved = true) }
                } else throw IOException()
            }
        } else {
            launch {
                if (saveShippingAddressUseCase(state.toShippingAddress()) == null) {
                    _state.update { it.copy(isSaved = true) }
                } else throw IOException()
            }
        }
    }

    private fun saveBillingAddress(state: State) {
        launch {
            saveBillingAddressUseCase(state.toBillingAddress())
            _state.update { it.copy(isSaved = true) }
        }
    }

    private fun onEmptyError() =
        _state.update { it.copy(textInputError = TextInputError.IsEmpty) }

    private fun launch(function: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(loading = true) }
                function()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.toError()) }
            } finally {
                _state.update { it.copy(loading = false) }
            }
        }
    }

    private fun State.toShippingAddress(): ShippingAddress =
        ShippingAddress(
            userId = userId!!,
            street = street!!,
            town = town!!,
            city = postalAddress?.city!!,
            state = postalAddress.state,
            zipCode = postalAddress.zipCode
        )

    private fun State.toBillingAddress(): com.piroworkz.architectcoders.domain.BillingAddress =
        com.piroworkz.architectcoders.domain.BillingAddress(
            userId = userId!!,
            street = street!!,
            town = town!!,
            city = postalAddress?.city!!,
            state = postalAddress.state,
            zipCode = postalAddress.zipCode
        )
}