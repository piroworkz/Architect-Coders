package com.piroworkz.architectcoders.app.ui.profile.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.databinding.DialogPaymentMethodBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentMethodDialog : BottomSheetDialogFragment() {

    private var _binding: DialogPaymentMethodBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentMethodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogPaymentMethodBinding.inflate(inflater, container, false)
        viewLifecycleOwner.collectFlow(
            viewModel.state,
            body = this@PaymentMethodDialog::configDataBindingVariables
        )
        return binding.root
    }

    private fun configDataBindingVariables(it: PaymentMethodViewModel.State) {
        if (it.isSaved) dismiss()
        binding.run {
            viewModel = this@PaymentMethodDialog.viewModel
            expirationDate = it.cardExpiration
            error = it.error
            savePaymentMethod = { this@PaymentMethodDialog.viewModel.saveCard(it) }
        }
    }

    companion object {
        const val TAG = "AddPaymentMethodDialogFragment"
    }
}
