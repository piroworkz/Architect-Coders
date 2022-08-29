package com.piroworkz.piroworkzmvvm.app.ui.shopping_cart

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.piroworkz.architectcoders.app.ui.shopping_cart.ShoppingCartAdapter
import com.piroworkz.architectcoders.domain.CartItem

@BindingAdapter("list")
fun RecyclerView.products(list: List<com.piroworkz.architectcoders.domain.CartItem>?) {
    list?.let {
        (adapter as ShoppingCartAdapter).submitList(it)
    }
}