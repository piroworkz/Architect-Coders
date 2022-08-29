package com.piroworkz.architectcoders.app.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.databinding.BottomSheetMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuDialog : BottomSheetDialogFragment() {

    private var _binding: BottomSheetMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var state: MenuState

    private val viewModel: MenuDialogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        state = buildState()

        _binding = BottomSheetMenuBinding.inflate(layoutInflater, container, false).apply {
            dismissDialog = { this@MenuDialog.dismiss() }
            navigationView.setNavigationItemSelectedListener(this@MenuDialog::onItemClick)
        }

        viewLifecycleOwner.collectFlow(viewModel.signOutState) { if (it) viewModel.signOut() }

        return binding.root
    }

    private fun onItemClick(it: MenuItem): Boolean {
        state.navigate(it, ::onSignOut)
        dismiss()
        return isDetached
    }

    private fun onSignOut() {
        viewModel.signOut()
        requireActivity().finishAffinity()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "MenuBottomSheetFragment"
    }
}