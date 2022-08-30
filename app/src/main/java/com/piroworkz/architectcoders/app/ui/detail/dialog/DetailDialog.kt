package com.piroworkz.architectcoders.app.ui.detail.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.databinding.BottomSheetDetailStoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailDialog : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDetailStoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var state: DetailDialogState
    
    private val detailDialogViewModel: DetailDialogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        state = buildState()
        _binding = BottomSheetDetailStoreBinding.inflate(inflater, container, false)

        viewLifecycleOwner.collectFlow(detailDialogViewModel.state) {
            it.toastMessage?.let(state::showToastMessage)
            if (it.dismissDialog) dismiss()
            configDataBindingVariables(it)
        }
        return binding.root
    }

    private fun configDataBindingVariables(it: DetailDialogViewModel.State) {
        binding.run { ->
            product = it.product
            size = it.size
            pieces = it.pieces
            viewModel = detailDialogViewModel
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        detailDialogViewModel.resetToast()
    }

    companion object {
        const val TAG = "DetailDialog"
    }
}