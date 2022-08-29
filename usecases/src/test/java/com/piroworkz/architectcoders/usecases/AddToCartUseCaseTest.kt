package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class AddToCartUseCaseTest {
    @Test
    fun `invoke calls user repository`(): Unit = runTest {
        val useCase =
            AddToCartUseCase(mock { onBlocking { addToShoppingCart(any()) } doReturn null })
        assertThat(useCase(any())).isNull()
    }
}