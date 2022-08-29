package com.piroworkz.architectcoders.app.ui.profile.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.architectcoders.app.ui.common.toError
import com.piroworkz.architectcoders.domain.Error
import com.piroworkz.architectcoders.domain.UserProfile
import com.piroworkz.architectcoders.usecases.CheckAuthProviderUseCase
import com.piroworkz.architectcoders.usecases.GetUserProfileUseCase
import com.piroworkz.architectcoders.usecases.RequestUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val requestUserProfileUseCase: RequestUserProfileUseCase,
    private val checkAuthProviderUseCase: CheckAuthProviderUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        launchDataLoad {
            getUserProfileUseCase().collect { userProfile: UserProfile ->
                userProfile.user?.let { requestUserProfileUseCase(it.email) }
                _state.update {
                    it.copy(
                        userProfile = userProfile,
                        isFacebookAccount = checkAuthProviderUseCase()
                    )
                }
            }
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                _state.update { it.copy(error = e.toError()) }
            }
        }

    data class State(
        val userProfile: UserProfile? = null,
        val isFacebookAccount: Boolean = false,
        val error: Error? = null
    )
}