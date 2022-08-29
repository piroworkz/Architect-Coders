package com.piroworkz.architectcoders.app.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.databinding.FragmentSplashAnimationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashAnimationFragment : Fragment(R.layout.fragment_splash_animation) {

    private lateinit var binding: FragmentSplashAnimationBinding
    private lateinit var state: SplashState

    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSplashAnimationBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        state = buildState()
        viewLifecycleOwner.collectFlow(viewModel.state) {
            binding.constraintLayout.setTransitionListener(state.listener(it))
        }
    }
}