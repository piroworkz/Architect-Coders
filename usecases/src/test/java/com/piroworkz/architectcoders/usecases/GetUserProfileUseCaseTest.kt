package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.testshared.userProfileFull
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetUserProfileUseCaseTest {

    @Test
    fun `invoke calls user repository`() {
        val userProfileFlow = flowOf(userProfileFull)
        val useCase = GetUserProfileUseCase(mock { on { userProfile } doReturn userProfileFlow })
        assertThat(useCase()).isEqualTo(userProfileFlow)
    }
}