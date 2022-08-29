package com.piroworkz.architectcoders.app.ui.profile.shipping

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.buildUserRepository
import com.piroworkz.architectcoders.app.data.FakeAndroidPermissionChecker
import com.piroworkz.architectcoders.app.data.FakeGMSLocationDataSource
import com.piroworkz.architectcoders.app.data.remote.datasource.FakeGeoNamesServerDataSource
import com.piroworkz.architectcoders.app.ui.profile.shipping.ShippingAddressDialogViewModel.State
import com.piroworkz.architectcoders.data.GeoPostalRepository
import com.piroworkz.architectcoders.data.source.LocationDataSource
import com.piroworkz.architectcoders.data.source.PermissionChecker
import com.piroworkz.architectcoders.data.source.PostalCodesDataSource
import com.piroworkz.architectcoders.domain.PostalAddress
import com.piroworkz.architectcoders.domain.UserProfile
import com.piroworkz.architectcoders.testshared.*
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import com.piroworkz.architectcoders.usecases.RequestAddressByZipCodeUseCase
import com.piroworkz.architectcoders.usecases.SaveBillingAddressUseCase
import com.piroworkz.architectcoders.usecases.SaveShippingAddressUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShippingAddressIntegrationTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    private val state = State().copy(userId = userProfileNew.user?.email)

    @Test
    fun `get user id from local database on view model init`() = runTest {

        val viewModel = buildViewModel(userProfile = userProfileNew)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())
        }
    }

    @Test
    fun `gets towns list from remote service`() = runTest {
        val viewModel = buildViewModel(userProfile = userProfileNew)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())
            viewModel.searchByZipCode(shippingAddress.zipCode)
            assertThat(postalAddressList.map { it.town }).isEqualTo(awaitItem().townsList)
            cancel()
        }
    }

    @Test
    fun `saves shipping and billing address to local database, if use same address is true`() =
        runTest {
            val actualState = state.copy(
                street = shippingAddress.street,
                town = shippingAddress.town,
                postalAddress = postalAddress,
                townsList = postalAddressList.map { it.town },
                useSameAddress = true
            )
            val viewModel =
                buildViewModel(
                    userProfile = userProfileNew,
                    defaultPostalList = postalAddressList
                )

            viewModel.state.test {
                assertThat(State()).isEqualTo(awaitItem())
                assertThat(state).isEqualTo(awaitItem())

                viewModel.onChecked()
                assertThat(awaitItem().useSameAddress).isTrue()

                viewModel.trySaveShippingAddressAddress(actualState)
                assertThat(awaitItem().isSaved).isTrue()
                cancel()
            }
        }

    @Test
    fun `saves on shipping address to local database if use same address is false`() = runTest {
        val actualState = state.copy(
            street = shippingAddress.street,
            town = shippingAddress.town,
            postalAddress = postalAddress,
            townsList = postalAddressList.map { it.town },
            useSameAddress = true
        )
        val viewModel =
            buildViewModel(
                userProfile = userProfileNew,
                defaultPostalList = postalAddressList
            )

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())

            viewModel.trySaveShippingAddressAddress(actualState)
            assertThat(awaitItem().isSaved).isTrue()
            cancel()
        }
    }
}

private fun buildViewModel(
    userProfile: UserProfile = userProfileNew,
    granted: Boolean = true,
    defaultPostalList: List<PostalAddress> = postalAddressList,
    defaultCountryCode: String = "MX"
): ShippingAddressDialogViewModel {
    val repository = buildUserRepository(userProfile = userProfile)
    val geoRepository = buildGeoRepository(granted, defaultPostalList, defaultCountryCode)

    val saveShippingAddressUseCase = SaveShippingAddressUseCase(repository)
    val saveBillingAddressUseCase = SaveBillingAddressUseCase(repository)
    val requestAddressByZipCode = RequestAddressByZipCodeUseCase(geoRepository)
    val getUserIdUseCase = GetUserIdUseCase(repository)

    return ShippingAddressDialogViewModel(
        saveShippingAddressUseCase,
        saveBillingAddressUseCase,
        requestAddressByZipCode,
        getUserIdUseCase
    )
}


private fun buildGeoRepository(
    granted: Boolean,
    defaultPostalList: List<PostalAddress>,
    defaultCountryCode: String
): GeoPostalRepository {
    val postalCodesDataSource: PostalCodesDataSource =
        FakeGeoNamesServerDataSource(defaultPostalList)
    val locationDataSource: LocationDataSource =
        FakeGMSLocationDataSource(defaultCountryCode)
    val checker: PermissionChecker =
        FakeAndroidPermissionChecker(granted)

    return GeoPostalRepository(postalCodesDataSource, locationDataSource, checker)
}