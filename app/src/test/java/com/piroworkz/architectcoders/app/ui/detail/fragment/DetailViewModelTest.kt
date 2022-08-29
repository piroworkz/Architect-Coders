package com.piroworkz.architectcoders.app.ui.detail.fragment

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.ui.detail.fragment.DetailViewModel.State
import com.piroworkz.architectcoders.testshared.product
import com.piroworkz.architectcoders.usecases.GetProductByIdUseCase
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
class DetailViewModelTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    @Mock
    lateinit var getProductByIdUseCase: GetProductByIdUseCase

    lateinit var viewModel: DetailViewModel

    private val productFlow = flowOf(product)

    private val state = State().copy(
        product = product,
    )

    @Before
    fun setup() {
        whenever(getProductByIdUseCase(product.model)).thenReturn(productFlow)
        viewModel = DetailViewModel(product.model, getProductByIdUseCase)
    }

    @Test
    fun `updates UI state with product on viewModel init`(): Unit = runTest {
        viewModel.state.test {
            awaitItem()
            assertThat(state).isEqualTo(awaitItem())
            cancel()
        }
    }

    @Test
    fun `calls get product by id use case`(): Unit = runTest {
        runCurrent()
        verify(getProductByIdUseCase).invoke(product.model)
    }
}