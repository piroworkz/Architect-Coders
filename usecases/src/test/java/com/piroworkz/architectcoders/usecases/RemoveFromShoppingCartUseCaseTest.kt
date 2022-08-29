package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class RemoveFromShoppingCartUseCaseTest {

    @Test
    operator fun invoke(): Unit = runTest {
        val useCase =
            RemoveFromShoppingCartUseCase(mock { onBlocking { removeFromShoppingCart(any()) } doReturn null })
        assertThat(useCase(any())).isNull()
    }
}