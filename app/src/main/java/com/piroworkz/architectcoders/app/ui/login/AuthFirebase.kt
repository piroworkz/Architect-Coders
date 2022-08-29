package com.piroworkz.architectcoders.app.ui.login

import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.piroworkz.architectcoders.R
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AuthFirebase(fragment: Fragment) {

    private var onResult: (Int) -> Unit = {}

    private val providers = listOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build(),
        AuthUI.IdpConfig.FacebookBuilder().build()
    )

    private val customLayout = AuthMethodPickerLayout
        .Builder(R.layout.firebase_login_method_picker)
        .setEmailButtonId(R.id.email_login_button)
        .setGoogleButtonId(R.id.google_login_button)
        .setFacebookButtonId(R.id.facebook_login_button)
        .build()

    private val authIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setLockOrientation(true)
        .setAvailableProviders(providers)
        .setAuthMethodPickerLayout(customLayout)
        .setIsSmartLockEnabled(false)
        .setTheme(R.style.Theme_LoginPicker)
        .build()

    private val launcher =
        fragment.registerForActivityResult(FirebaseAuthUIActivityResultContract()) {
            onResult(it.resultCode)
        }

    suspend fun launchSignIn(): Int {
        return suspendCancellableCoroutine { continuation ->
            onResult = { code: Int ->
                continuation.resume(code)
            }
            launcher.launch(authIntent)
        }
    }
}

