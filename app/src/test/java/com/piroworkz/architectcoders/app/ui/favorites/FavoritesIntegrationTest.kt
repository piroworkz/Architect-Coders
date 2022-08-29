package com.piroworkz.architectcoders.app.ui.favorites

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.buildStoreRepository
import com.piroworkz.architectcoders.app.ui.favorites.FavoritesViewModel.State
import com.piroworkz.architectcoders.domain.Product
import com.piroworkz.architectcoders.testshared.favoritesList
import com.piroworkz.architectcoders.usecases.EmptyFavoritesUseCase
import com.piroworkz.architectcoders.usecases.GetProductsUseCase
import com.piroworkz.architectcoders.usecases.SwitchFavoritesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoritesIntegrationTest {

    @get:Rule
    val rule = CoroutinesTestRule()

    private val state = State().copy(favorites = favoritesList.filter { it.favorite })

    @Test
    fun `get favorites from local database`() = runTest {
        val viewModel = buildViewModel(products = favoritesList)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())
            cancel()
        }
    }

    @Test
    fun `flips favorite value if true to false and if false to true`(): Unit = runTest {
        val favoritesListSize: Int = favoritesList.filter { it.favorite }.size
        val viewModel = buildViewModel(products = favoritesList)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())
            viewModel.switchFavorite(favoritesList.first())
            assertThat(favoritesListSize - 1).isEqualTo(awaitItem().favorites?.size)
        }
    }

    @Test
    fun `change all products favorites value to false on local database`(): Unit = runTest {

        val viewModel = buildViewModel(products = favoritesList)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())
            viewModel.emptyFavorite()
            assertThat(awaitItem().favorites).isEmpty()
        }
    }
}

private fun buildViewModel(
    products: List<Product> = emptyList(),
): FavoritesViewModel {
    val storeRepository = buildStoreRepository(product = products)
    val getProductsUseCase = GetProductsUseCase(storeRepository)
    val switchFavoritesUseCase = SwitchFavoritesUseCase(storeRepository)
    val emptyFavoritesUseCase = EmptyFavoritesUseCase(storeRepository)

    return FavoritesViewModel(getProductsUseCase, switchFavoritesUseCase, emptyFavoritesUseCase)
}