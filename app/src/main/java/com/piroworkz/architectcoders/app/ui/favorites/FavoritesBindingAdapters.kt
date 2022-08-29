package com.piroworkz.piroworkzmvvm.app.ui.favorites

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.piroworkz.architectcoders.domain.Product

@BindingAdapter("submitList")
fun RecyclerView.submitList(list: List<com.piroworkz.architectcoders.domain.Product>?) {
    list?.let((adapter as FavoritesAdapter)::submitList)
}