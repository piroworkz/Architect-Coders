package com.piroworkz.architectcoders.app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.architectcoders.app.ui.common.toError
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.usecases.CreateNewUserAccountUseCase
import com.piroworkz.architectcoders.usecases.GetFirebaseUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getFirebaseUserUseCase: GetFirebaseUserUseCase,
    private val createNewUserAccountUseCase: CreateNewUserAccountUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun onResultOk() {
        launchScope {
            val user = getFirebaseUserUseCase()
            if (user != null) {
                _state.update { it.copy(loggedIn = createNewUserAccountUseCase(user) == null) }
            } else throw IOException()
        }
    }

    data class State(
        val loggedIn: Boolean = false,
        val error: Error? = null
    )

    private fun launchScope(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.toError()) }
            } finally {
            }
        }
    }

}