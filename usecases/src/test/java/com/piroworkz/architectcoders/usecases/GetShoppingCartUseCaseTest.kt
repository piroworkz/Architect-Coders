package com.piroworkz.architectcoders.usecases

import com.google.common.truth.Truth.assertThat
import com.piroworkz.architectcoders.testshared.cartItemList
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetShoppingCartUseCaseTest {

    @Test
    fun `invoke calls store repository`() {
        val shoppingCartFlow = flowOf(cartItemList)
        val useCase = GetShoppingCartUseCase(mock { on { shoppingCart } doReturn shoppingCartFlow })

        assertThat(useCase()).isEqualTo(shoppingCartFlow)
    }
}