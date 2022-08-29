package com.piroworkz.architectcoders.app.ui.splash

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @get:Rule
    val rule = CoroutinesTestRule()

    @Mock
    lateinit var getUserIdUseCase: GetUserIdUseCase

    private lateinit var vm: SplashViewModel

    @Before
    fun setUp() {
        vm = SplashViewModel(getUserIdUseCase)
    }

    @Test
    fun `update UI with user id on view model init`(): Unit = runTest {
        val userId = "id"
        whenever(getUserIdUseCase()).thenReturn(userId)

        vm.state.test {
            awaitItem()
            assertThat(userId).isNotNull()
            cancel()
        }
    }

    @Test
    fun `calls get user id use case`(): Unit = runTest {
        runCurrent()
        verify(getUserIdUseCase).invoke()
    }
}