package com.piroworkz.architectcoders.app.ui.detail.fragment

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.app.ui.detail.fragment.DetailViewModel.State
import com.piroworkz.architectcoders.databinding.FragmentDetailStoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail_store) {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var state: DetailState
    private lateinit var binding: FragmentDetailStoreBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailStoreBinding.bind(view).apply { ->
            (pinchZoom.background as AnimationDrawable).start()
            toolbar.apply { ->
                setupWithNavController(findNavController())
                setOnMenuItemClickListener(::navigateTo)
            }
        }
        state = buildState(args.model)
        viewLifecycleOwner
            .collectFlow(viewModel.state, body = this::configDataBindingVariables)
    }

    private fun configDataBindingVariables(it: State) {
        binding.run {
            product = it.product
            openDialog = { state.openDialog(childFragmentManager) }
        }
    }

    private fun navigateTo(item: MenuItem): Boolean {
        state.navigate(item.itemId)
        return true
    }
}