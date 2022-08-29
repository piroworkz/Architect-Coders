package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.StoreRepository
import com.piroworkz.architectcoders.domain.CartItem
import com.piroworkz.architectcoders.domain.Error
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val repository: StoreRepository) {
    suspend operator fun invoke(item: CartItem): Error? = repository.addToShoppingCart(item)
}