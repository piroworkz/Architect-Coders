package com.piroworkz.architectcoders.app.ui.profile.shipping

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.ui.profile.shipping.ShippingAddressDialogViewModel.State
import com.piroworkz.architectcoders.domain.TextInputError.ZipCode
import com.piroworkz.architectcoders.testshared.*
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import com.piroworkz.architectcoders.usecases.RequestAddressByZipCodeUseCase
import com.piroworkz.architectcoders.usecases.SaveBillingAddressUseCase
import com.piroworkz.architectcoders.usecases.SaveShippingAddressUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ShippingAddressDialogViewModelTest {

    @get:Rule
    val rule = CoroutinesTestRule()

    @Mock
    lateinit var saveShippingAddressUseCase: SaveShippingAddressUseCase

    @Mock
    lateinit var saveBillingAddressUseCase: SaveBillingAddressUseCase

    @Mock
    lateinit var requestAddressByZipCodeUseCase: RequestAddressByZipCodeUseCase

    @Mock
    lateinit var getUserIdUseCase: GetUserIdUseCase

    private lateinit var viewModel: ShippingAddressDialogViewModel
    private val listOfPostalAddress = postalAddressList
    private val address = shippingAddress
    private val state = State().copy(userId = "email")

    @Before
    fun setUp(): Unit = runTest {
        whenever(saveShippingAddressUseCase(any())).thenReturn(null)
        whenever(saveBillingAddressUseCase(any())).thenReturn(null)
        whenever(requestAddressByZipCodeUseCase(any())).thenReturn(listOfPostalAddress)
        whenever(getUserIdUseCase()).thenReturn("email")

        viewModel = ShippingAddressDialogViewModel(
            saveShippingAddressUseCase,
            saveBillingAddressUseCase,
            requestAddressByZipCodeUseCase,
            getUserIdUseCase
        )
    }

    @Test
    fun `updates state with user id`(): Unit = runTest {
        runCurrent()
        viewModel.state.test {
            assertThat(state).isEqualTo(awaitItem())
        }
    }

    @Test
    fun `updates state with street value`(): Unit = runTest {
        viewModel.onInputStreet(address.street)
        viewModel.state.test {
            assertThat(address.street).isEqualTo(awaitItem().street)
        }
    }

    @Test
    fun `update state with town value`(): Unit = runTest {
        viewModel.onInputTown(address.town)
        viewModel.state.test {
            assertThat(address.town.lowercase())
                .isEqualTo(awaitItem().town?.lowercase())
        }
    }

    @Test
    fun `update state with postal address`(): Unit = runTest {
        viewModel.searchByZipCode(listOfPostalAddress[0].zipCode)
        viewModel.state.test {
            awaitItem()
            assertThat(listOfPostalAddress[0]).isEqualTo(awaitItem().postalAddress)
        }
    }

    @Test
    fun `update state with error ZipCode if return list is null`(): Unit = runTest {
        val error = ZipCode(address.zipCode)
        whenever(requestAddressByZipCodeUseCase(address.zipCode)).thenReturn(null)

        viewModel.searchByZipCode(address.zipCode)

        viewModel.state.test {
            assertThat(awaitItem().textInputError).isNull()
            assertThat(error).isEqualTo(awaitItem().textInputError)
        }
    }

    @Test
    fun `saves address as shipping if use same address is false`(): Unit = runTest {
        val state = state.copy(
            town = address.town,
            street = address.street,
            postalAddress = postalAddress,
            useSameAddress = false
        )

        viewModel.trySaveShippingAddressAddress(state)

        viewModel.state.test {
            awaitItem()
            assertThat(awaitItem().isSaved).isTrue()
        }
    }

    @Test
    fun `saves address as both shipping and billing if use same address is true`(): Unit = runTest {
        val state = state.copy(
            town = address.town,
            street = address.street,
            postalAddress = postalAddress,
            useSameAddress = true
        )

        viewModel.trySaveShippingAddressAddress(state)

        viewModel.state.test {
            awaitItem()
            assertThat(awaitItem().isSaved).isTrue()
        }
    }

    @Test
    fun `calls save shipping address use case`(): Unit = runTest {
        val state = state.copy(
            town = address.town,
            street = address.street,
            postalAddress = postalAddress,
            useSameAddress = false
        )
        viewModel.trySaveShippingAddressAddress(state)
        runCurrent()

        verify(saveShippingAddressUseCase).invoke(any())
    }

    @Test
    fun `calls save billing address use case`(): Unit = runTest {
        val state = state.copy(
            town = address.town,
            street = address.street,
            postalAddress = postalAddress,
            useSameAddress = true
        )
        viewModel.trySaveBillingAddressAddress(state)
        runCurrent()

        verify(saveBillingAddressUseCase).invoke(any())
    }

    @Test
    fun `sets error value to null`(): Unit = runTest {
        viewModel.clearError()
        viewModel.state.test {
            assertThat(awaitItem().textInputError).isNull()
        }
    }

    @Test
    fun `sets use same address value to true`(): Unit = runTest {
        viewModel.onChecked()
        viewModel.state.test {
            assertThat(awaitItem().useSameAddress).isTrue()
        }
    }

}