package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class RequestProductsUseCaseTest {
    @Test
    fun `invoke calls store repository`(): Unit = runTest {
        val useCase =
            RequestProductsUseCase(mock { onBlocking { requestProducts() } doReturn null })
        assertThat(useCase()).isNull()
    }
}