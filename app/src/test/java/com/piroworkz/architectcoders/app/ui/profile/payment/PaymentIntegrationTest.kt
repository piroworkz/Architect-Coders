package com.piroworkz.architectcoders.app.ui.profile.payment

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.buildUserRepository
import com.piroworkz.architectcoders.app.ui.profile.payment.PaymentMethodViewModel.State
import com.piroworkz.architectcoders.domain.TextInputError
import com.piroworkz.architectcoders.domain.UserProfile
import com.piroworkz.architectcoders.testshared.user
import com.piroworkz.architectcoders.testshared.userProfileNew
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import com.piroworkz.architectcoders.usecases.SavePaymentMethodUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PaymentIntegrationTest {

    @get:Rule
    val rule = CoroutinesTestRule()

    private val state = State().copy(
        userId = user.email,
        cardHolder = "CARD HOLDER",
        cardNumber = "0000000000000000",
        cardExpiration = "12/25",
        cardIssuer = "CARD ISSUER",
        cardCvc = "000"
    )

    @Test
    fun `saves payment method if `(): Unit = runTest {

        val viewModel = buildViewModel(userProfileNew)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            viewModel.setCardHolder("CARD HOLDER")
            viewModel.setCardIssuer("CARD ISSUER")
            viewModel.setCardExpiration("12/25")
            viewModel.setCardNUmber("0000000000000000")
            viewModel.setCardCvc("000")
            assertThat(state).isEqualTo(awaitItem())
            viewModel.saveCard(state)
            assertThat(awaitItem().isSaved).isTrue()
        }
    }

    @Test
    fun `sets error if form is not validated`() = runTest {
        val error = TextInputError.IsEmpty
        val viewModel = buildViewModel(userProfileNew)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            viewModel.saveCard(State())
            assertThat(error).isEqualTo(awaitItem().error)
            viewModel.clearError()
            assertThat(awaitItem().error).isNull()
        }
    }
}

private fun buildViewModel(
    userProfile: UserProfile
): PaymentMethodViewModel {
    val repository = buildUserRepository(userProfile = userProfile)
    val getUserIdUseCase = GetUserIdUseCase(repository)
    val savePaymentMethodUseCase = SavePaymentMethodUseCase(repository)
    return PaymentMethodViewModel(getUserIdUseCase, savePaymentMethodUseCase)
}