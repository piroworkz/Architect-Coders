package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.UserRepository
import com.piroworkz.architectcoders.domain.Error
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(): Error? = repository.signOut()
}