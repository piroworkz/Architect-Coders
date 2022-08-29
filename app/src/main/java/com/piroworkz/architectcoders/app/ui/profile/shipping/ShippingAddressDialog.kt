package com.piroworkz.architectcoders.app.ui.profile.shipping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.databinding.DialogShippingAddressBinding
import com.piroworkz.piroworkzmvvm.app.ui.profile.shipping.AddShippingAddressState
import com.piroworkz.piroworkzmvvm.app.ui.profile.shipping.buildState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShippingAddressDialog : BottomSheetDialogFragment() {

    private lateinit var state: AddShippingAddressState

    private var _binding: DialogShippingAddressBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShippingAddressDialogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogShippingAddressBinding.inflate(inflater, container, false)

        state = buildState()

        viewLifecycleOwner.collectFlow(viewModel.state) {
            configDataBindingVariables(it)
            if (it.isSaved && it.useSameAddress) dismiss()
            if (it.isSaved && !it.useSameAddress) {
                dismiss()
                state.openAddBillingAddressDialog(parentFragmentManager)
            }
        }
        return binding.root
    }

    private fun configDataBindingVariables(state: ShippingAddressDialogViewModel.State) {
        binding.run {
            error = state.textInputError
            loading = state.loading
            address = state.postalAddress
            viewModel = this@ShippingAddressDialog.viewModel
            towns = this@ShippingAddressDialog.state.getList(state)
            saveAddress =
                { this@ShippingAddressDialog.viewModel.trySaveShippingAddressAddress(state) }
        }
    }

    companion object {
        const val TAG = "ShippingAddressDialog"
    }
}
