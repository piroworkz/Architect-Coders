package com.piroworkz.architectcoders.app.ui.profile.shipping

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.profile.payment.PaymentMethodViewModel
import com.piroworkz.architectcoders.domain.TextInputError
import com.piroworkz.architectcoders.domain.TextInputError.IsEmpty
import com.piroworkz.architectcoders.domain.TextInputError.ZipCode

@BindingAdapter("setList")
fun AutoCompleteTextView.setList(list: List<String>?) {
    list?.let {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this.context, android.R.layout.simple_list_item_1, it)
        setAdapter(adapter)
    }
}

@BindingAdapter("addTextChangedListener")
fun TextInputEditText.addTextChangedListener(viewModel: ShippingAddressDialogViewModel?) {
    addTextChangedListener {
        when (id) {
            R.id.streetET -> viewModel?.onInputStreet(text.toString())
            R.id.zipCodeET -> viewModel?.searchByZipCode(text.toString())
        }
    }
}

@BindingAdapter("addTextChangedListener")
fun AutoCompleteTextView.addTextChangedListener(viewModel: ShippingAddressDialogViewModel?) {
    addTextChangedListener { viewModel?.onInputTown(text.toString()) }
}

@BindingAdapter("addTextChangedListener")
fun TextInputEditText.addTextChangedListener(viewModel: PaymentMethodViewModel?) {
    addTextChangedListener {
        when (id) {
            R.id.cardHolder -> viewModel?.setCardHolder(text.toString())
            R.id.cardIssuer -> viewModel?.setCardIssuer(text.toString())
            R.id.cardExpiration -> viewModel?.setCardExpiration(text.toString())
            R.id.cardNumber -> viewModel?.setCardNUmber(text.toString())
            R.id.cardCvc -> viewModel?.setCardCvc(text.toString())
        }
    }
}

@BindingAdapter("setOnFocusChangeListener")
fun TextInputEditText.setOnFocusChangeListener(viewModel: PaymentMethodViewModel?) {
    setOnFocusChangeListener { _, b ->
        if (b) {
            viewModel?.clearError()
            error = null
        }
    }
}

@BindingAdapter("setOnFocusChangeListener")
fun TextInputEditText.setOnFocusChangeListener(viewModel: ShippingAddressDialogViewModel?) {
    setOnFocusChangeListener { _, b ->
        if (b) {
            viewModel?.clearError()
            error = null
        }
    }
}

@BindingAdapter("setOnFocusChangeListener")
fun AutoCompleteTextView.setOnFocusChangeListener(viewModel: ShippingAddressDialogViewModel?) {
    setOnFocusChangeListener { _, b ->
        if (b) {
            viewModel?.clearError()
            error = null
        }
    }
}

@BindingAdapter("message")
fun TextInputLayout.helperText(error: com.piroworkz.architectcoders.domain.TextInputError?) {
    error.let {
        helperText = when (error) {
            is IsEmpty -> context.getString(R.string.empty_string_error)
            is ZipCode -> context.getString(R.string.zip_code_error, error.error)
            null -> ""
        }
    }
}

@BindingAdapter("onError")
fun TextInputEditText.onError(error: com.piroworkz.architectcoders.domain.TextInputError?) {
    when (error) {
        is IsEmpty -> if (text?.isEmpty() == true) {
            this.error = context.getString(R.string.empty_string_error)
        }
        is ZipCode -> this.error =
            context.getString(R.string.zip_code_error, error.error)
        null -> this.error = null
    }
}

@BindingAdapter("onError")
fun AutoCompleteTextView.onError(error: com.piroworkz.architectcoders.domain.TextInputError?) {
    when (error) {
        is IsEmpty -> {
            if (text?.isEmpty() == true)
                this.error = context.getString(R.string.empty_string_error)
        }
        null -> this.error = null
        else -> {}
    }
}

@BindingAdapter("formatExpiration")
fun TextInputEditText.formatExpiration(date: String?) {
    date?.let {
        if (it.length == 4) {
            this.setText(
                context.getString(
                    R.string.expiration_date_format,
                    date.take(2),
                    date.takeLast(2)
                )
            )
        }
    }
}