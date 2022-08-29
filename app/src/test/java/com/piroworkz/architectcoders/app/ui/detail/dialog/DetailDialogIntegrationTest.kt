package com.piroworkz.architectcoders.app.ui.detail.dialog

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.app.CoroutinesTestRule
import com.piroworkz.architectcoders.app.buildStoreRepository
import com.piroworkz.architectcoders.app.buildUserRepository
import com.piroworkz.architectcoders.app.ui.detail.dialog.DetailDialogViewModel.State
import com.piroworkz.architectcoders.domain.Product
import com.piroworkz.architectcoders.domain.UserProfile
import com.piroworkz.architectcoders.testshared.productListOf
import com.piroworkz.architectcoders.testshared.user
import com.piroworkz.architectcoders.testshared.userProfileFull
import com.piroworkz.architectcoders.usecases.AddToCartUseCase
import com.piroworkz.architectcoders.usecases.GetProductByIdUseCase
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailDialogIntegrationTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    private val state = State().copy(
        userId = user.email,
        product = productListOf[0]
    )

    @Test
    fun `userId and product are loaded from local database on viewModel init`(): Unit =
        runTest {
            val viewModel = buildDialogViewModel(
                products = productListOf,
                userProfile = userProfileFull
            )
            viewModel.state.test {
                assertThat(State()).isEqualTo(awaitItem())
                assertThat(state).isEqualTo(awaitItem())
            }
        }

    @Test
    fun `adds product to shopping cart in local database`(): Unit = runTest {

        val actualState = state.copy(
            toastMessage = productListOf[0].title,
            dismissDialog = true
        )
        val viewModel = buildDialogViewModel(
            products = productListOf,
            userProfile = userProfileFull
        )
        viewModel.addToCart()

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(actualState).isEqualTo(awaitItem())
            cancel()
        }
    }

    @Test
    fun `sets errorMessage if getUserIdUseCase Fails`(): Unit = runTest {

        val viewModel = buildDialogViewModel(
            products = productListOf,
            userProfile = userProfileFull.copy(user = null)
        )

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(awaitItem().error).contains("Error de base de datos")
        }
    }

    @Test
    fun `sets errorMessage if getProductByIdUseCase fails`(): Unit = runTest {

        val viewModel = buildDialogViewModel(model = null)

        viewModel.state.test {
            assertThat(State()).isEqualTo(awaitItem())
            assertThat(awaitItem().error).contains("Error desconocido")
            cancel()
        }
    }
}

private fun buildDialogViewModel(
    model: String? = productListOf[0].model,
    products: List<Product> = emptyList(),
    userProfile: UserProfile = userProfileFull
): DetailDialogViewModel {
    val storeRepository = buildStoreRepository(products)
    val userRepository = buildUserRepository(userProfile = userProfile)
    val getProductByIdUseCase = GetProductByIdUseCase(storeRepository)
    val addToCartUseCase = AddToCartUseCase(storeRepository)
    val getUserIdUseCase = GetUserIdUseCase(userRepository)

    return DetailDialogViewModel(model, getProductByIdUseCase, addToCartUseCase, getUserIdUseCase)
}

