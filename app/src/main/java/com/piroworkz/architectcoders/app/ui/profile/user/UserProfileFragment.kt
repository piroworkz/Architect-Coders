package com.piroworkz.architectcoders.app.ui.profile.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.app.ui.common.setUpNavBar
import com.piroworkz.architectcoders.databinding.FragmentUserProfileBinding
import com.piroworkz.architectcoders.domain.UserProfile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {

    private lateinit var binding: FragmentUserProfileBinding
    private lateinit var state: UserProfileState

    private val viewModel: UserProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentUserProfileBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        state = buildState()
        setUpNavBar(binding.toolbar)
        viewLifecycleOwner.collectFlow(viewModel.state, body = this::configDataBindingVariables)
    }

    private fun configDataBindingVariables(it: UserProfileViewModel.State) {
        binding.run {
            appBarHeader.imageUrl = state.getPhotoUrl(it)
            it.userProfile?.let { profile: UserProfile ->
                user = profile.user
                billingAddress = profile.billingAddress
                shippingAddress = profile.shippingAddress
                paymentMethod = profile.paymentMethod
            }
            editShippingAddressButton =
                { state.openAddShippingAddressFragment(childFragmentManager) }
            editPaymentButton =
                { state.openAddPaymentMethodDialog(childFragmentManager) }
        }
    }
}