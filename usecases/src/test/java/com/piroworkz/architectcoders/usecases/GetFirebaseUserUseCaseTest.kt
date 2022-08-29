package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.testshared.user
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GetFirebaseUserUseCaseTest {

    @Test
    fun `invoke calls user repository`(): Unit = runTest {
        val useCase =
            GetFirebaseUserUseCase(mock { onBlocking { getUserFromFirebase() } doReturn user })
        assertThat(useCase()).isEqualTo(user)
    }
}