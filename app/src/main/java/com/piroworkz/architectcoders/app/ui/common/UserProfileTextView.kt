package com.piroworkz.architectcoders.app.ui.common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.piroworkz.architectcoders.domain.BillingAddress
import com.piroworkz.architectcoders.domain.PaymentMethod
import com.piroworkz.architectcoders.domain.ShippingAddress
import com.piroworkz.architectcoders.domain.User

class UserProfileTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    fun setText(user: com.piroworkz.architectcoders.domain.User?) {
        if (user != null) {
            text = buildSpannedString {
                bold { appendLine("Email:") }
                appendLine(user.email)
                bold { appendLine("Teléfono:") }
                appendLine(user.phone.ifEmpty { "No disponible" })
            }
        }
    }

    fun setText(shippingAddress: com.piroworkz.architectcoders.domain.ShippingAddress?) {
        if (shippingAddress != null) {
            text = buildSpannedString {
                bold { appendLine("Domicilio:") }
                appendLine(shippingAddress.street)
                bold { appendLine("Colonia:") }
                appendLine(shippingAddress.town)
                bold { appendLine("Municipio/Alcaldía:") }
                appendLine(shippingAddress.city)
                bold { appendLine("Estado:") }
                appendLine(shippingAddress.state)
                bold { appendLine("Código Postal:") }
                appendLine(shippingAddress.zipCode)
            }
        }
    }

    fun setText(billingAddress: com.piroworkz.architectcoders.domain.BillingAddress?) {
        if (billingAddress != null) {
            text = buildSpannedString {
                bold { appendLine("Domicilio:") }
                appendLine(billingAddress.street)
                bold { appendLine("Colonia:") }
                appendLine(billingAddress.town)
                bold { appendLine("Municipio/Alcaldía:") }
                appendLine(billingAddress.city)
                bold { appendLine("Estado:") }
                appendLine(billingAddress.state)
                bold { appendLine("Código Postal:") }
                appendLine(billingAddress.zipCode)
            }
        }
    }

    fun setText(paymentMethod: com.piroworkz.architectcoders.domain.PaymentMethod?) {
        if (paymentMethod != null) {
            text = buildSpannedString {
                bold { appendLine("Titular:") }
                appendLine(paymentMethod.card_holder)
                bold { appendLine("Banco Emisor:") }
                appendLine(paymentMethod.card_issuer)
                bold { appendLine("Tarjeta Débito/Crédito:") }
                appendLine("****-****-****-${paymentMethod.card_number.takeLast(4)}")
            }
        }
    }

}
