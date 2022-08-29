package com.piroworkz.architectcoders.app.ui.common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.piroworkz.architectcoders.domain.CartItem
import com.piroworkz.architectcoders.domain.Product

class TotalPiecesTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    fun setText(cartItem: com.piroworkz.architectcoders.domain.CartItem) {
        text = buildSpannedString {
            if (cartItem.smallSize != 0) {
                bold { append("Talla CH: ") }
                append(cartItem.smallSize.toString())
                append(" ", pieces(cartItem.smallSize))
                appendLine("")
            }
            if (cartItem.mediumSize != 0) {
                bold { append("Talla M: ") }
                append(cartItem.mediumSize.toString())
                append(" ", pieces(cartItem.mediumSize))
                appendLine("")
            }
            if (cartItem.largeSize != 0) {
                bold { append("Talla GDE: ") }
                append(cartItem.largeSize.toString())
                append(" ", pieces(cartItem.largeSize))
                appendLine("")
            }
        }
    }

    fun setAvailableText(product: com.piroworkz.architectcoders.domain.Product) {
        text = buildSpannedString {
            if (product.smallSize != 0) {
                bold { append("Talla CH: ") }
                append(product.smallSize.toString())
                append(" ", pieces(product.smallSize))
                append(" ", available(product.smallSize))
                appendLine("")
            }
            if (product.mediumSize != 0) {
                bold { append("Talla M: ") }
                append(product.mediumSize.toString())
                append(" ", pieces(product.mediumSize))
                append(" ", available(product.mediumSize))
                appendLine("")
            }
            if (product.largeSize != 0) {
                bold { append("Talla GDE: ") }
                append(product.largeSize.toString())
                append(" ", pieces(product.largeSize))
                append(" ", available(product.largeSize))
                appendLine("")
            }
        }
    }

    private fun available(pieces: Int): String =
        if (pieces == 1) {
            "disponible"
        } else {
            "disponibles"
        }


    private fun pieces(pieces: Int): String =
        if (pieces == 1) {
            "Pieza"
        } else {
            "Piezas"
        }

}