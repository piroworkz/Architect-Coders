package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.StoreRepository
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.Product
import javax.inject.Inject

class SwitchFavoritesUseCase @Inject constructor(val repository: StoreRepository) {
    suspend operator fun invoke(product: Product): Error? {
        println("SwitchFavoritesUseCase")
        return repository.switchFavorite(product)
    }
}