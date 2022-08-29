package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.StoreRepository
import javax.inject.Inject

class CountProductsUseCase @Inject constructor(private val repository: StoreRepository) {
    suspend operator fun invoke() = repository.countProducts()
}