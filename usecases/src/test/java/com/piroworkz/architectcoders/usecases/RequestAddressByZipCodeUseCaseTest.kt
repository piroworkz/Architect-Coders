package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.testshared.postalAddressList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class RequestAddressByZipCodeUseCaseTest {

    @Test
    fun `invoke calls geo postal repository`(): Unit = runTest {
        val listPostalAddress = postalAddressList
        val useCase =
            RequestAddressByZipCodeUseCase(mock { onBlocking { requestAddressByZipCode("0") } doReturn listPostalAddress })
        assertThat(useCase("0")).isEqualTo(listPostalAddress)
    }
}