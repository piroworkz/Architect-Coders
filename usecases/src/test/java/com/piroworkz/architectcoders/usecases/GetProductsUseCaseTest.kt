package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.testshared.productListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GetProductsUseCaseTest {

    @Test
    fun `invoke calls store repository`(): Unit = runTest {
        val productListFlow = flowOf(productListOf)
        val useCase = GetProductsUseCase(mock { on { products } doReturn productListFlow })

        assertThat(useCase()).isEqualTo(productListFlow)
    }
}