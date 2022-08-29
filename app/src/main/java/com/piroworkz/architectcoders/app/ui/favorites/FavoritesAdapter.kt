package com.piroworkz.piroworkzmvvm.app.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.piroworkz.architectcoders.databinding.FavoriteBinding
import com.piroworkz.architectcoders.domain.Product

class FavoritesAdapter(
    private val listener: (Product) -> Unit,
    private val onItemCLicked: (String) -> Unit
) :
    ListAdapter<Product, FavoritesViewHolder>(FavoritesDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder =
        FavoritesViewHolder.from(parent)

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position), listener, onItemCLicked)
    }
}

class FavoritesViewHolder(private val binding: FavoriteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: Product,
        listener: (Product) -> Unit,
        onItemCLicked: (String) -> Unit
    ) {
        binding.run {
            product = item
            sizeAvailable.setAvailableText(product!!)
            switchFavorite = { listener(item) }
            onClickedItem = { onItemCLicked(item.model) }
        }
    }

    companion object {
        fun from(parent: ViewGroup): FavoritesViewHolder {
            val binding =
                FavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FavoritesViewHolder(binding)
        }
    }
}

class FavoritesDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.model == newItem.model

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem == newItem
}