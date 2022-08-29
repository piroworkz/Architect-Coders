package com.piroworkz.architectcoders.app.ui.login

import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.piroworkz.architectcoders.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun LoginFragment.buildState(
    navController: NavController = findNavController(),
    scope: CoroutineScope = lifecycleScope,
    authFirebase: AuthFirebase = AuthFirebase(this)
): LoginState = LoginState(navController, scope, authFirebase)

class LoginState(
    private val navController: NavController,
    private val scope: CoroutineScope,
    private val auth: AuthFirebase,
) {

    fun launchSignIn(onResult: (Int) -> Unit) {
        scope.launch {
            val result: Int = auth.launchSignIn()
            onResult(result)
        }
    }

    fun onLoggedIn(loggedIn: Boolean) {
        if (loggedIn) {
            navController.apply {
                popBackStack(R.id.loginFragment, true)
                navigate(R.id.mainStoreFragment)
            }
        }
    }
}