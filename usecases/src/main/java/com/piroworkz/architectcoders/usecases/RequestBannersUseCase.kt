package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.StoreRepository
import com.piroworkz.architectcoders.domain.Error
import javax.inject.Inject

class RequestBannersUseCase @Inject constructor(private val repository: StoreRepository) {
    suspend operator fun invoke(): Error? = repository.requestBanners()
}