package com.piroworkz.architectcoders.app.ui.store.utils

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class ProductsLayoutManager(context: Context) :
    GridLayoutManager(context, 6, VERTICAL, false) {

    init {
        spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position % 6) {
                    0 -> 6
                    in 1..3 -> 2
                    else -> 3
                }
            }
        }
    }

}