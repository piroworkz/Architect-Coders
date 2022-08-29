package com.piroworkz.architectcoders.data.source

import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.User

interface AuthenticationDatasource {
    enum class ProviderId(val id: String) { FACEBOOK("facebook.com") }

    fun signOut(): Error?
    suspend fun getUserFromFirebase(): User?
    fun checkAuthProvider(): Boolean
}