package com.piroworkz.architectcoders.app.ui.profile.user

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.ui.profile.user.UserProfileViewModel.State
import com.piroworkz.architectcoders.testshared.*
import com.piroworkz.architectcoders.usecases.CheckAuthProviderUseCase
import com.piroworkz.architectcoders.usecases.GetUserProfileUseCase
import com.piroworkz.architectcoders.usecases.RequestUserProfileUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserProfileViewModelTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    @Mock
    lateinit var getUserProfileUseCase: GetUserProfileUseCase

    @Mock
    lateinit var requestUserProfileUseCase: RequestUserProfileUseCase

    @Mock
    lateinit var checkAuthProviderUseCase: CheckAuthProviderUseCase

    private lateinit var viewModel: UserProfileViewModel
    private val userFlow = flowOf(userProfileNew)
    private val state = State().copy(
        userProfile = userProfileNew
    )

    @Before
    fun setUp(): Unit = runTest {
        whenever(getUserProfileUseCase()).thenReturn(userFlow)
        whenever(requestUserProfileUseCase(any())).thenReturn(null)
        whenever(checkAuthProviderUseCase()).thenReturn(true)

        viewModel = UserProfileViewModel(
            getUserProfileUseCase,
            requestUserProfileUseCase,
            checkAuthProviderUseCase
        )

    }

    @Test
    fun `updates state with user profile`(): Unit = runTest {
        viewModel.state.test {
            assertThat(state.userProfile).isEqualTo(awaitItem().userProfile)
        }
    }

    @Test
    fun `calls get user id use case`(): Unit = runTest {
        runCurrent()
        verify(requestUserProfileUseCase).invoke(any())
    }

    @Test
    fun `calls request user profile use case`(): Unit = runTest {
        runCurrent()
        verify(requestUserProfileUseCase).invoke(any())
    }

    @Test
    fun `calls check auth provider use case`(): Unit = runTest {
        runCurrent()
        verify(checkAuthProviderUseCase).invoke()
    }

    @Test
    fun `calls get user profile use case`(): Unit = runTest {
        runCurrent()
        verify(getUserProfileUseCase).invoke()
    }

}