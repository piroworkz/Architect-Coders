package com.piroworkz.architectcoders.app.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.architectcoders.app.ui.common.toError
import com.piroworkz.architectcoders.usecases.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuDialogViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
) :
    ViewModel() {

    private val _signOut = MutableStateFlow(false)
    val signOutState get() = _signOut.asStateFlow()

    fun signOut() {
        launchScope {
            if (signOutUseCase() == null) {
                _signOut.value = true
            }
        }
    }

    private fun launchScope(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                println(e.toError())
            }
        }
    }
}