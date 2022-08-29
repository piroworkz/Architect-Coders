package com.piroworkz.architectcoders.app.ui.detail.fragment

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.buildStoreRepository
import com.piroworkz.architectcoders.app.ui.detail.fragment.DetailViewModel.State
import com.piroworkz.architectcoders.domain.Product
import com.piroworkz.architectcoders.testshared.productListOf
import com.piroworkz.architectcoders.usecases.GetProductByIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailFragmentIntegrationTest {

    @get:Rule
    val rule = CoroutinesTestRule()

    private val state = State().copy(product = productListOf[0])

    @Test
    fun `get product from local database on viewModel init`() = runTest {
        val viewModel = buildViewModel(
            products = productListOf,
        )
        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(state).isEqualTo(awaitItem())
        }
    }

    @Test
    fun `sets errorMessage if GetProductByIdUseCase fails`() = runTest {
        buildViewModel().state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(awaitItem().error).isEqualTo("Error desconocido: null")
        }
    }
}

private fun buildViewModel(
    model: String? = productListOf[0].model,
    products: List<Product> = emptyList(),
): DetailViewModel {
    val repository = buildStoreRepository(products)
    val getProductByIdUseCase = GetProductByIdUseCase(repository)
    return DetailViewModel(model!!, getProductByIdUseCase)
}