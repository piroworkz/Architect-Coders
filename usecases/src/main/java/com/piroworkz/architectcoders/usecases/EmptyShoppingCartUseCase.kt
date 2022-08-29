package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.StoreRepository
import com.piroworkz.architectcoders.domain.CartItem
import com.piroworkz.architectcoders.domain.Error
import javax.inject.Inject

class EmptyShoppingCartUseCase @Inject constructor(val repository: StoreRepository) {
    suspend operator fun invoke(shoppingCart: List<CartItem>): Error? = repository.emptyShoppingCart(shoppingCart)
}