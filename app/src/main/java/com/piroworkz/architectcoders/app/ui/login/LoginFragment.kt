package com.piroworkz.architectcoders.app.ui.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.databinding.FragmentFirebaseLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_firebase_login) {

    private lateinit var state: LoginState
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFirebaseLoginBinding.bind(view)
        state = buildState().also { state: LoginState -> state.launchSignIn { onResult(it) } }
        viewLifecycleOwner.collectFlow(viewModel.state) {
            binding.loading = it.loggedIn
            state.onLoggedIn(it.loggedIn)
        }
    }

    private fun onResult(it: Int) {
        when (it) {
            Activity.RESULT_OK -> viewModel.onResultOk()
            Activity.RESULT_CANCELED -> requireActivity().finishAffinity()
        }
    }
}
