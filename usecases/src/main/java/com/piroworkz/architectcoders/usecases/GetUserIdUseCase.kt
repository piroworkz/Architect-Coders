package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.UserRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(): String? = repository.getUserId()
}