package com.piroworkz.architectcoders.app.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.architectcoders.app.ui.common.logMessage
import com.piroworkz.architectcoders.usecases.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    getUserIdUseCase: GetUserIdUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<String?> = MutableStateFlow(null)
    val state: StateFlow<String?> = _state.asStateFlow()

    init {
        try {
            viewModelScope.launch { _state.value = getUserIdUseCase() }
        } catch (e: Exception) {
            e.message?.logMessage()
        }
    }

}