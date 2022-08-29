package com.piroworkz.piroworkzmvvm.app.ui.profile.shipping

import androidx.fragment.app.FragmentManager
import com.piroworkz.architectcoders.app.ui.profile.shipping.ShippingAddressDialogViewModel
import com.piroworkz.architectcoders.app.ui.profile.billing.BillingAddressDialog
import com.piroworkz.architectcoders.app.ui.profile.shipping.ShippingAddressDialog

class AddShippingAddressState {

    fun getList(state: ShippingAddressDialogViewModel.State): List<String>? =
        if (state.townsList.isNotEmpty()) {
            val sortedList = state.townsList
            state.town?.let { s: String -> sortedList.filter { it.contains(s) } }
        } else {
            emptyList()
        }

    fun openAddBillingAddressDialog(manager: FragmentManager) {
        val dialog = BillingAddressDialog()
        if (!dialog.isAdded) {
            dialog.show(manager, BillingAddressDialog.TAG)
        }
    }
}

fun ShippingAddressDialog.buildState() = AddShippingAddressState()
fun BillingAddressDialog.buildState() = AddShippingAddressState()