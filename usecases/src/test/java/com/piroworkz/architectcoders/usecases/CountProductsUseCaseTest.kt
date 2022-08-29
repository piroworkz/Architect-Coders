package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.testshared.productListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class CountProductsUseCaseTest {

    @Test
    fun `invoke calls store repository`() = runTest {
        val userCase =
            CountProductsUseCase(mock { onBlocking { countProducts() } doReturn productListOf.size })
        assertThat(productListOf.size).isEqualTo(userCase())
    }
}