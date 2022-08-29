package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.StoreRepository
import com.piroworkz.architectcoders.domain.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: StoreRepository) {
    operator fun invoke(): Flow<List<Product>> = repository.products
}