package com.piroworkz.architectcoders.app.ui.profile.payment

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.ui.profile.payment.PaymentMethodViewModel.State
import com.piroworkz.architectcoders.domain.TextInputError
import com.piroworkz.architectcoders.testshared.paymentMethod
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import com.piroworkz.architectcoders.usecases.SavePaymentMethodUseCase
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
class PaymentMethodViewModelTest {

    @get:Rule
    val rule = CoroutinesTestRule()

    @Mock
    lateinit var getUserIdUseCase: GetUserIdUseCase

    @Mock
    lateinit var savePaymentMethodUseCase: SavePaymentMethodUseCase

    private lateinit var viewModel: PaymentMethodViewModel

    private val state = State().copy(userId = paymentMethod.userId)

    @Before
    fun setUp(): Unit = runTest {
        whenever(getUserIdUseCase()).thenReturn(paymentMethod.userId)
        whenever(savePaymentMethodUseCase(paymentMethod)).thenReturn(null)
        viewModel = PaymentMethodViewModel(getUserIdUseCase, savePaymentMethodUseCase)
    }

    @Test
    fun `updates state with user id`(): Unit = runTest {
        viewModel.state.test {
            assertThat(state).isEqualTo(awaitItem())
        }
    }

    @Test
    fun `updates state with card holder value`(): Unit = runTest {
        viewModel.setCardHolder(paymentMethod.card_holder)
        viewModel.state.test {
            assertThat(paymentMethod.card_holder).isEqualTo(awaitItem().cardHolder)
        }
    }

    @Test
    fun `updates state with card issuer`(): Unit = runTest {
        viewModel.setCardIssuer(paymentMethod.card_issuer)
        viewModel.state.test {
            assertThat(paymentMethod.card_issuer)
                .isEqualTo(awaitItem().cardIssuer)
        }
    }

    @Test
    fun `updates state with card expiration`(): Unit = runTest {
        viewModel.setCardExpiration(paymentMethod.card_expiration)
        viewModel.state.test {
            assertThat(paymentMethod.card_expiration)
                .isEqualTo(awaitItem().cardExpiration)
        }
    }

    @Test
    fun `updates state with card number`(): Unit = runTest {
        viewModel.setCardNUmber(paymentMethod.card_number)
        viewModel.state.test {
            assertThat(paymentMethod.card_number)
                .isEqualTo(awaitItem().cardNumber)
        }
    }

    @Test
    fun `updates state with card cvc`(): Unit = runTest {
        viewModel.setCardCvc(paymentMethod.card_cvc)
        viewModel.state.test {
            assertThat(paymentMethod.card_cvc)
                .isEqualTo(awaitItem().cardCvc)
        }
    }

    @Test
    fun `updates state with null error`(): Unit = runTest {
        viewModel.clearError()
        viewModel.state.test {
            assertThat(awaitItem().error).isNull()
        }
    }

    @Test
    fun `saves card if form is valid`(): Unit = runTest {
        val actualState = state.copy(
            paymentMethod.userId, paymentMethod.card_holder, paymentMethod.card_issuer,
            paymentMethod.card_expiration, paymentMethod.card_number, paymentMethod.card_cvc
        )
        viewModel.saveCard(actualState)

        viewModel.state.test {
            awaitItem()
            assertThat(awaitItem().isSaved).isTrue()
        }
    }

    @Test
    fun `sets error value to IsEmpty`(): Unit = runTest {
        val error = TextInputError.IsEmpty
        viewModel.saveCard(state)
        viewModel.state.test {
            awaitItem()
            assertThat(error).isEqualTo(awaitItem().error)
        }
    }

    @Test
    fun `calls get user id use case`(): Unit = runTest {
        verify(getUserIdUseCase).invoke()
    }

    @Test
    fun `calls save payment method use case`(): Unit = runTest {
        val actualState = state.copy(
            paymentMethod.userId, paymentMethod.card_holder, paymentMethod.card_issuer,
            paymentMethod.card_expiration, paymentMethod.card_number, paymentMethod.card_cvc
        )
        viewModel.saveCard(actualState)
        runCurrent()
        verify(savePaymentMethodUseCase).invoke(paymentMethod)
    }
}