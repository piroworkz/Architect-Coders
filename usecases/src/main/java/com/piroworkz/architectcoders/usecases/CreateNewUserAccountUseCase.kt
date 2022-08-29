package com.piroworkz.architectcoders.usecases

import com.piroworkz.architectcoders.data.UserRepository
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.User
import javax.inject.Inject

class CreateNewUserAccountUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: User): Error? = repository.createNewUserAccount(user)
}