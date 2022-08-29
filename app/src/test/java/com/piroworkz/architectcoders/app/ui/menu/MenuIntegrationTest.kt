package com.piroworkz.architectcoders.app.ui.menu

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.buildUserRepository
import com.piroworkz.architectcoders.domain.UserProfile
import com.piroworkz.architectcoders.testshared.userProfileFull
import com.piroworkz.architectcoders.usecases.SignOutUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MenuIntegrationTest {

    @get:Rule
    val rule = CoroutinesTestRule()

    @Test
    fun `sets sign out state to true is user is signed out out`() = runTest {

        val viewModel = buildViewModel(false)

        viewModel.signOut()

        viewModel.signOutState.test {
            assertThat(false).isEqualTo(awaitItem())
            assertThat(true).isEqualTo(awaitItem())
        }

    }
}

private fun buildViewModel(
    isLoggedIn: Boolean = true,
    userProfile: UserProfile = userProfileFull
): MenuDialogViewModel {
    val userRepository = buildUserRepository(isLoggedIn, userProfile)
    return MenuDialogViewModel(SignOutUseCase(userRepository))
}