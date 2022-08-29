package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.testshared.bannerList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GetBannersUseCaseTest {

    @Test
    fun `invoke calls store repository`(): Unit = runTest {
        val bannersFlow = flowOf(bannerList)
        val useCase = GetBannersUseCase(mock { onBlocking { banners } doReturn bannersFlow })
        assertThat(useCase()).isEqualTo(bannersFlow)
    }
}