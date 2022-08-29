package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.StoreRepository
import javax.inject.Inject

class EmptyFavoritesUseCase @Inject constructor(private val repository: StoreRepository) {
    suspend operator fun invoke() = repository.emptyFavorites()
}