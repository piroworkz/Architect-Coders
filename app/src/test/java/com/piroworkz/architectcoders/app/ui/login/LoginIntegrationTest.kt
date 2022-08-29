package com.piroworkz.architectcoders.app.ui.login

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.buildUserRepository
import com.piroworkz.architectcoders.app.ui.login.LoginViewModel.State
import com.piroworkz.architectcoders.testshared.userProfileFull
import com.piroworkz.architectcoders.usecases.CreateNewUserAccountUseCase
import com.piroworkz.architectcoders.usecases.GetFirebaseUserUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginIntegrationTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    @Test
    fun `on logged in result ok creates a new user account on local database`(): Unit = runTest {

        val viewModel = buildViewModel()
        viewModel.onResultOk()

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(awaitItem().loggedIn).isTrue()
        }
    }

    @Test
    fun `set errorMessage if firebase user is null`(): Unit = runTest {
        val viewModel = buildViewModel(isLoggedIn = false)
        viewModel.onResultOk()

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(awaitItem().error).isNotNull()
        }
    }
}

private fun buildViewModel(isLoggedIn: Boolean = true): LoginViewModel {
    val userRepository = buildUserRepository(isLoggedIn, userProfileFull)
    val getFirebaseUserUseCase = GetFirebaseUserUseCase(userRepository)
    val createNewUserAccountUseCase = CreateNewUserAccountUseCase(userRepository)
    return LoginViewModel(getFirebaseUserUseCase, createNewUserAccountUseCase)
}