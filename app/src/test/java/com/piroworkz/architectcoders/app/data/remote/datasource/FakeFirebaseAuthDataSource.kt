package com.piroworkz.architectcoders.app.data.remote.datasource

import com.piroworkz.architectcoders.app.ui.common.tryCatch
import com.piroworkz.architectcoders.data.source.AuthenticationDatasource
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.User

class FakeFirebaseAuthDataSource(isLoggedIn: Boolean, private val authProvider: String) :
    AuthenticationDatasource {

    private val user: User? = if (isLoggedIn) {
        User(
            "David Luna",
            "email@gmail.com",
            "98r374fa098523f",
            "5613580695",
            "http.myPhotoUrl.com"
        )
    } else null

    override fun signOut(): Error? =
        tryCatch {
            println("CALLED FirebaseAuthFake:signOut")
        }
            .fold(
                ifLeft = { it },
                ifRight = { null }
            )

    override suspend fun getUserFromFirebase(): User? = tryCatch {
        user
    }.fold(
        ifLeft = { null },
        ifRight = { it }
    )

    override fun checkAuthProvider(): Boolean {
        val result = authProvider.contains("Facebook")
        println("CALLED FirebaseAuthFake:checkAuthProvider $result")
        return result
    }

}