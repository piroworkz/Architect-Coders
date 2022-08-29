package com.piroworkz.architectcoders.app.ui.login

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.ui.login.LoginViewModel.State
import com.piroworkz.architectcoders.testshared.user
import com.piroworkz.architectcoders.usecases.CreateNewUserAccountUseCase
import com.piroworkz.architectcoders.usecases.GetFirebaseUserUseCase
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
class LoginViewModelTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    @Mock
    lateinit var getFirebaseUserUseCase: GetFirebaseUserUseCase

    @Mock
    lateinit var createNewUserAccountUseCase: CreateNewUserAccountUseCase

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp(): Unit = runTest {
        whenever(getFirebaseUserUseCase()).thenReturn(user)
        whenever(createNewUserAccountUseCase(user)).thenReturn(null)

        viewModel = LoginViewModel(getFirebaseUserUseCase, createNewUserAccountUseCase)
    }

    @Test
    fun `update ui state with login status`(): Unit = runTest {
        val actualState = State().copy(loggedIn = true)


        viewModel.onResultOk()
        viewModel.state.test {
            awaitItem()
            assertThat(actualState).isEqualTo(awaitItem())
        }

    }

    @Test
    fun `calls get firebase user use case`(): Unit = runTest {
        viewModel.onResultOk()
        runCurrent()
        verify(getFirebaseUserUseCase).invoke()
    }

    @Test
    fun `calls create new user account use case`(): Unit = runTest {
        viewModel.onResultOk()
        runCurrent()
        verify(createNewUserAccountUseCase).invoke(user)
    }
}