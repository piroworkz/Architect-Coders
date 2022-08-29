package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.StoreRepository
import com.piroworkz.architectcoders.domain.CartItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoppingCartUseCase @Inject constructor(private val repository: StoreRepository) {
    operator fun invoke(): Flow<List<CartItem>> = repository.shoppingCart
}