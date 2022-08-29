package com.piroworkz.piroworkzmvvm.app.ui.profile.user

import androidx.databinding.BindingAdapter
import com.piroworkz.architectcoders.app.ui.common.UserProfileTextView

@BindingAdapter("setText")
fun <T : Any> UserProfileTextView.setText(entry: T?) {
    if (entry != null) {
        setText(entry)
    }
}