package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.UserRepository
import com.piroworkz.architectcoders.domain.Error
import javax.inject.Inject

class RequestUserProfileUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String): Error? = userRepository.requestUserProfile(email)
}