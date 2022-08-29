package com.piroworkz.architectcoders.app.ui.profile.user

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.facebook.AccessToken
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.profile.payment.PaymentMethodDialog
import com.piroworkz.architectcoders.app.ui.profile.shipping.ShippingAddressDialog

class UserProfileState(
    private val context: Context,
) {

    fun getPhotoUrl(it: UserProfileViewModel.State): String? {
        val url = if (it.isFacebookAccount) {
            context.getString(
                R.string.photo_url,
                it.userProfile?.user?.photoUrl,
                AccessToken.getCurrentAccessToken()?.token
            )
        } else {
            it.userProfile?.user?.photoUrl
        }
        return url
    }

    fun openAddShippingAddressFragment(
        manager: FragmentManager,
    ) {
        val dialog = ShippingAddressDialog()
        if (!dialog.isAdded) dialog.show(
            manager,
            ShippingAddressDialog.TAG
        )
    }

    fun openAddPaymentMethodDialog(manager: FragmentManager) {
        val dialog = PaymentMethodDialog()
        if (!dialog.isAdded)
            dialog.show(manager, PaymentMethodDialog.TAG)
    }
}

fun UserProfileFragment.buildState(context: Context = requireContext()) =
    UserProfileState(context)
