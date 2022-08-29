package com.piroworkz.architectcoders.app.ui.menu

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.usecases.SignOutUseCase
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
class MenuDialogViewModelTest {

    @get:Rule
    val rule = CoroutinesTestRule()

    @Mock
    lateinit var signOutUseCase: SignOutUseCase

    private lateinit var viewModel: MenuDialogViewModel

    @Before
    fun setup(): Unit = runTest {
        whenever(signOutUseCase()).thenReturn(null)
        viewModel = MenuDialogViewModel(signOutUseCase)
    }

    @Test
    fun `on sign out clicked changes state to true`(): Unit = runTest {
        viewModel.signOut()
        runCurrent()

        viewModel.signOutState.test {
            assertThat(awaitItem()).isTrue()
            cancel()
        }
    }

    @Test
    fun `calls sign out use case`(): Unit = runTest {
        viewModel.signOut()
        runCurrent()

        verify(signOutUseCase).invoke()
    }
}