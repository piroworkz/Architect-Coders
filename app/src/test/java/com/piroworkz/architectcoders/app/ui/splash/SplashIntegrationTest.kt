package com.piroworkz.architectcoders.app.ui.splash

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.buildUserRepository
import com.piroworkz.architectcoders.domain.UserProfile
import com.piroworkz.architectcoders.testshared.*
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashIntegrationTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    private val userId = user.email

    @Test
    fun `gets user id from local database on view model init`() = runTest {
        val viewModel = buildViewModel(userProfileNew)

        viewModel.state.test {
            assertThat(awaitItem()).isNull()
            assertThat(userId).isEqualTo(awaitItem())
        }
    }
}

private fun buildViewModel(
    userProfile: UserProfile = userProfileNew
): SplashViewModel {
    val repository = buildUserRepository(userProfile = userProfile)
    val getUserIdUseCase = GetUserIdUseCase(repository)

    return SplashViewModel(getUserIdUseCase)
}