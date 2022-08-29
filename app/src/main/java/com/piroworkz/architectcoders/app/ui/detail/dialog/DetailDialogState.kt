package com.piroworkz.architectcoders.app.ui.detail.dialog

import android.content.Context
import android.widget.Toast
import com.piroworkz.architectcoders.R

class DetailDialogState(private val context: Context) {

    fun showToastMessage(message: String?) {
        if (message != null) {
            Toast.makeText(
                context,
                context.getString(R.string.toast_cart, message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

fun DetailDialog.buildState(
): DetailDialogState = DetailDialogState(requireContext())
