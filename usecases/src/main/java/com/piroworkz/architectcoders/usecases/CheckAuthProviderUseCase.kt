package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.UserRepository
import javax.inject.Inject

class CheckAuthProviderUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(): Boolean =
        userRepository.checkAuthProvider()
}