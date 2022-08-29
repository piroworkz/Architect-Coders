package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.testshared.product
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GetProductByIdUseCaseTest {

    @Test
    fun `invoke calls user repository`(): Unit = runTest {
        val productFlow = flowOf(product)
        val useCase =
            GetProductByIdUseCase(mock { onBlocking { getProductById(any()) } doReturn productFlow })

        assertThat(useCase(any())).isEqualTo(productFlow)
    }
}