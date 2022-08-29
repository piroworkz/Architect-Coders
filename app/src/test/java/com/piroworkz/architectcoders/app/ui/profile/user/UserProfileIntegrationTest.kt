package com.piroworkz.architectcoders.app.ui.profile.user

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.buildUserRepository
import com.piroworkz.architectcoders.app.ui.profile.user.UserProfileViewModel.State
import com.piroworkz.architectcoders.domain.UserProfile
import com.piroworkz.architectcoders.testshared.userProfileNew
import com.piroworkz.architectcoders.usecases.CheckAuthProviderUseCase
import com.piroworkz.architectcoders.usecases.GetUserProfileUseCase
import com.piroworkz.architectcoders.usecases.RequestUserProfileUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserProfileIntegrationTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    private val state = State().copy(userProfile = userProfileNew, isFacebookAccount = true)

    @Test
    fun `on viewModel init gets user profile info from local data source, and checks authentication provider id`() =
        runTest {
            val viewModel = buildViewModel(userProfileNew)

            viewModel.state.test {
                assertThat(State()).isEqualTo(awaitItem())
                assertThat(state).isEqualTo(awaitItem())
            }
        }
}

fun buildViewModel(
    userProfile: UserProfile,
    authProvider: String = "Facebook"
): UserProfileViewModel {
    val userRepository = buildUserRepository(
        userProfile = userProfile,
        authProvider = authProvider
    )

    val getUserProfileUseCase = GetUserProfileUseCase(userRepository)
    val requestUserProfileUseCase = RequestUserProfileUseCase(userRepository)
    val checkAuthProviderUseCase = CheckAuthProviderUseCase(userRepository)

    return UserProfileViewModel(
        getUserProfileUseCase,
        requestUserProfileUseCase,
        checkAuthProviderUseCase
    )
}