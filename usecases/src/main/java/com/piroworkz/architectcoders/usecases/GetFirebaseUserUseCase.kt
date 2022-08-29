package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.UserRepository
import com.piroworkz.architectcoders.domain.User
import javax.inject.Inject

class GetFirebaseUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(): User? = repository.getUserFromFirebase()
}