package com.piroworkz.architectcoders.app.ui.detail.dialog

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.ui.detail.dialog.DetailDialogViewModel.State
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.Size
import com.piroworkz.architectcoders.testshared.product
import com.piroworkz.architectcoders.testshared.user
import com.piroworkz.architectcoders.usecases.AddToCartUseCase
import com.piroworkz.architectcoders.usecases.GetProductByIdUseCase
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import io.americanexpress.busybee.BusyBee
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
class DetailDialogViewModelTest {

    @get:Rule
    val rule = CoroutinesTestRule()

    @Mock
    lateinit var getProductByIdUseCase: GetProductByIdUseCase

    @Mock
    lateinit var addToCartUseCase: AddToCartUseCase

    @Mock
    lateinit var getUserIdUseCase: GetUserIdUseCase

    @Mock
    lateinit var busyBee: BusyBee

    private lateinit var viewModel: DetailDialogViewModel

    private val productFlow = flowOf(product)

    private val state = State().copy(
        product = product,
        userId = user.email,
        size = null,
        pieces = 0,
        toastMessage = null,
        dismissDialog = false,
        error = null
    )

    @Before
    fun setUp(): Unit = runTest {
        whenever(getUserIdUseCase()).thenReturn(user.email)
        whenever(getProductByIdUseCase(product.model)).thenReturn(productFlow)
        viewModel = DetailDialogViewModel(
            product.model,
            getProductByIdUseCase,
            addToCartUseCase,
            getUserIdUseCase,
            busyBee
        )
    }

    @Test
    fun `Ui is updated with product and user id on viewModel init`(): Unit = runTest {
        viewModel.state.test {
            assertThat(state).isEqualTo(awaitItem())
            cancel()
        }
    }

    @Test
    fun `size value is updated when chip is selected`(): Unit = runTest {
        val size = Size.SMALL
        val actualState = state.copy(size = size)

        viewModel.onSizeChipSelected(size)

        viewModel.state.test {
            assertThat(actualState.size).isEqualTo(awaitItem().size)
            cancel()
        }
    }

    @Test
    fun `should reset size state to null`(): Unit = runTest {

        viewModel.onSizeChipSelected(Size.SMALL)
        viewModel.resetSize()

        viewModel.state.test {
            assertThat(awaitItem().size).isNull()
            cancel()
        }
    }

    @Test
    fun `should add one to pieces`(): Unit = runTest {
        val actualState = state.copy(pieces = 1)

        viewModel.onSizeChipSelected(Size.SMALL)
        viewModel.addToPieces()

        viewModel.state.test {
            assertThat(actualState.pieces).isEqualTo(awaitItem().pieces)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should subtract one from pieces if it != 0`(): Unit = runTest {
        val actualState = state.copy(pieces = 1)

        viewModel.onSizeChipSelected(Size.SMALL)
        viewModel.addToPieces()
        viewModel.addToPieces()
        viewModel.subtractFromPieces()

        viewModel.state.test {
            assertThat(actualState.pieces).isEqualTo(awaitItem().pieces)
            cancel()
        }
    }

    @Test
    fun `calls add to cart use case`(): Unit = runTest {
        viewModel.addToCart()
        runCurrent()

        verify(addToCartUseCase).invoke(any())
    }

    @Test
    fun `sets errorMessage if add to cart use case fails`(): Unit = runTest {
        whenever(addToCartUseCase(any())).thenReturn(Error.Server)
        viewModel.addToCart()
        runCurrent()
        viewModel.state.test {
            assertThat(awaitItem().error).isNotNull()
        }
    }

    @Test
    fun `state is updated after item is added to cart`(): Unit = runTest {
        val actualState = state.copy(toastMessage = state.product?.title, dismissDialog = true)
        whenever(addToCartUseCase(any())).thenReturn(null)

        viewModel.addToCart()

        viewModel.state.test {
            awaitItem()
            assertThat(actualState).isEqualTo(awaitItem())
            cancel()
        }
    }

    @Test
    fun `sets dismissDialog value to true`(): Unit = runTest {
        viewModel.dismissDialog()

        viewModel.state.test {
            assertThat(awaitItem().dismissDialog).isTrue()
            cancel()
        }
    }

    @Test
    fun `sets toastMessage value to null`(): Unit = runTest {
        viewModel.resetToast()

        viewModel.state.test {
            assertThat(awaitItem().toastMessage).isNull()
        }
    }
}