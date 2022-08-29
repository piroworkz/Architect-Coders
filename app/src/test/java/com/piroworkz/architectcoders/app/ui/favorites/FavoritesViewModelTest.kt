package com.piroworkz.architectcoders.app.ui.favorites

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.ui.favorites.FavoritesViewModel.State
import com.piroworkz.architectcoders.testshared.favoritesList
import com.piroworkz.architectcoders.testshared.product
import com.piroworkz.architectcoders.usecases.EmptyFavoritesUseCase
import com.piroworkz.architectcoders.usecases.GetProductsUseCase
import com.piroworkz.architectcoders.usecases.SwitchFavoritesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoritesViewModelTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    @Mock
    lateinit var getProductsUseCase: GetProductsUseCase

    @Mock
    lateinit var switchFavoritesUseCase: SwitchFavoritesUseCase

    @Mock
    lateinit var emptyFavoritesUseCase: EmptyFavoritesUseCase

    private lateinit var viewModel: FavoritesViewModel


    private val state = State().copy(favorites = favoritesList.filter { it.favorite })

    @Before
    fun setUp() {
        whenever(getProductsUseCase()).thenReturn(flowOf(favoritesList))
        viewModel =
            FavoritesViewModel(getProductsUseCase, switchFavoritesUseCase, emptyFavoritesUseCase)
    }

    @Test
    fun `update state with a list of products marked as favorites`(): Unit = runTest {
        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())
            cancel()
        }
    }

    @Test
    fun `calls get favorites use case`(): Unit = runTest {
        runCurrent()
        verify(getProductsUseCase).invoke()
    }

    @Test
    fun `calls switch favorite use case`(): Unit = runTest {
        viewModel.switchFavorite(product)
        runCurrent()
        verify(switchFavoritesUseCase).invoke(product)
    }

    @Test
    fun `calls empty favorites use case`(): Unit = runTest {
        viewModel.emptyFavorite()
        runCurrent()
        verify(emptyFavoritesUseCase).invoke()
    }
}