package com.piroworkz.architectcoders.app.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import com.piroworkz.architectcoders.app.data.utils.toDomain
import com.piroworkz.architectcoders.app.ui.common.tryCatch
import com.piroworkz.architectcoders.data.source.AuthenticationDatasource
import com.piroworkz.architectcoders.data.source.AuthenticationDatasource.ProviderId.FACEBOOK
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.User
import javax.inject.Inject

class FirebaseAuthDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth) : AuthenticationDatasource {
    override fun signOut(): Error? =
        tryCatch { firebaseAuth.signOut() }
            .fold(
                ifLeft = { it },
                ifRight = { null }
            )

    override suspend fun getUserFromFirebase(): User? = tryCatch {
        firebaseAuth.currentUser.toDomain()
    }.fold(
        ifLeft = { null },
        ifRight = { it }
    )

    override fun checkAuthProvider(): Boolean =
        firebaseAuth.currentUser?.providerData?.get(1)?.providerId == FACEBOOK.id
}
