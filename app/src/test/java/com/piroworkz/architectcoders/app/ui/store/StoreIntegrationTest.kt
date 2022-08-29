package com.piroworkz.architectcoders.app.ui.store

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.buildStoreRepository
import com.piroworkz.architectcoders.app.ui.store.StoreViewModel.State
import com.piroworkz.architectcoders.domain.Product
import com.piroworkz.architectcoders.testshared.*
import com.piroworkz.architectcoders.usecases.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StoreIntegrationTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    private val state = State().copy(products = productListOf, banners = bannerList)

    @Test
    fun `on view model init gets products and banners from local data base`() = runTest {
        val viewModel = buildViewModel(productListOf)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())
        }
    }

    @Test
    fun `if products is null or empty requests data from firebase and saves it to local data base`() =
        runTest {
            val viewModel = buildViewModel()

            viewModel.state.test {
                assertThat(State()).isEqualTo(awaitItem())
                assertThat(state.copy(products = emptyList())).isEqualTo(awaitItem())

                viewModel.onPermissionRequestResult()
                assertThat(state).isEqualTo(awaitItem())
            }
        }
}

private fun buildViewModel(
    productList: List<Product> = emptyList()
): StoreViewModel {
    val repository = buildStoreRepository(productList)

    val requestProducts = RequestProductsUseCase(repository)
    val requestBanners = RequestBannersUseCase(repository)
    val products = GetProductsUseCase(repository)
    val banners = GetBannersUseCase(repository)
    val switchFavoriteUseCase = SwitchFavoritesUseCase(repository)

    return StoreViewModel(
        requestProducts,
        requestBanners,
        products,
        banners,
        switchFavoriteUseCase
    )
}