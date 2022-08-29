package com.piroworkz.architectcoders.app.ui.profile.billing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.app.ui.profile.shipping.ShippingAddressDialogViewModel
import com.piroworkz.architectcoders.databinding.DialogShippingAddressBinding
import com.piroworkz.piroworkzmvvm.app.ui.profile.shipping.AddShippingAddressState
import com.piroworkz.piroworkzmvvm.app.ui.profile.shipping.buildState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BillingAddressDialog : BottomSheetDialogFragment() {

    private var _binding: DialogShippingAddressBinding? = null
    private val binding get() = _binding!!
    private lateinit var state: AddShippingAddressState

    private val viewModel: ShippingAddressDialogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogShippingAddressBinding.inflate(inflater, container, false)

        state = buildState()
        viewLifecycleOwner.collectFlow(viewModel.state) {
            configDataBindingVariables(it)
            if (it.isSaved) dismiss()
        }
        return binding.root
    }

    private fun configDataBindingVariables(state: ShippingAddressDialogViewModel.State) {
        binding.run {
            error = state.textInputError
            loading = state.loading
            address = state.postalAddress
            viewModel = this@BillingAddressDialog.viewModel
            towns = this@BillingAddressDialog.state.getList(state)
            saveAddress =
                { this@BillingAddressDialog.viewModel.trySaveBillingAddressAddress(state) }
            useSameAddress.visibility = View.GONE
            toolbar.title = getString(R.string.billing_address_title)
        }
    }

    companion object {
        const val TAG = "BillingAddressDialog"
    }
}