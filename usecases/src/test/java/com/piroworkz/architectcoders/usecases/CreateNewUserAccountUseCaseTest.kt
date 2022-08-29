package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class CreateNewUserAccountUseCaseTest {

    @Test
    fun `invoke calls user repository`(): Unit = runTest {
        val userCase =
            CreateNewUserAccountUseCase(mock { onBlocking { createNewUserAccount(any()) } doReturn null })
        Truth.assertThat(userCase(any())).isNull()
    }
}