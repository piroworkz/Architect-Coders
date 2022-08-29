package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class SignOutUseCaseTest {
    @Test
    fun `invoke calls user repository`(): Unit = runTest {
        val useCase = SignOutUseCase(mock { onBlocking { signOut() } doReturn null })
        assertThat(useCase()).isNull()
    }
}