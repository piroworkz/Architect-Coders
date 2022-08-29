package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.testshared.user
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetUserIdUseCaseTest {

    @Test
    fun `invoke calls user repository`(): Unit = runTest {
        val userId = user.email
        val useCase = GetUserIdUseCase(mock { onBlocking { getUserId() } doReturn userId })

        assertThat(useCase()).isEqualTo(userId)
    }
}