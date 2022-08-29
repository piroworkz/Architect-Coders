package com.piroworkz.architectcoders.app.ui.store

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.testshared.*
import com.piroworkz.architectcoders.usecases.*
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
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StoreViewModelTest {

    @get:Rule
    val rule = CoroutinesTestRule()

    @Mock
    lateinit var requestProductsUseCase: RequestProductsUseCase

    @Mock
    lateinit var requestBannersUseCase: RequestBannersUseCase

    @Mock
    lateinit var getProductsUseCase: GetProductsUseCase

    @Mock
    lateinit var getBannersUseCase: GetBannersUseCase

    @Mock
    lateinit var switchFavoritesUseCase: SwitchFavoritesUseCase

    private lateinit var viewModel: StoreViewModel


    private val state = StoreViewModel.State().copy(
        products = productListOf, banners = bannerList
    )

    @Before
    fun setUp(): Unit = runTest {
        whenever(getProductsUseCase()).thenReturn(flowOf(productListOf))
        whenever(getBannersUseCase()).thenReturn(flowOf(bannerList))
        whenever(switchFavoritesUseCase(any())).thenReturn(null)

        viewModel = StoreViewModel(
            requestProductsUseCase,
            requestBannersUseCase,
            getProductsUseCase,
            getBannersUseCase,
            switchFavoritesUseCase
        )
    }

    @Test
    fun `update state with a list of products and a list of banners`(): Unit = runTest {

        viewModel.state.test {
            assertThat(state).isEqualTo(awaitItem())
            cancel()
        }
    }

    @Test
    fun `calls switch favorite use case`(): Unit = runTest {
        viewModel.switchFavorite(product)
        runCurrent()
        verify(switchFavoritesUseCase).invoke(product)
    }

    @Test
    fun `calls get products use case`() {
        verify(getProductsUseCase).invoke()
        verify(getBannersUseCase).invoke()
    }
}