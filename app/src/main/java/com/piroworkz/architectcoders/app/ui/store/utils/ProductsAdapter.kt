package com.piroworkz.architectcoders.app.ui.store.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.piroworkz.architectcoders.databinding.ProductBinding
import com.piroworkz.architectcoders.domain.Product

class ProductsAdapter(
    private val onItemClicked: (String) -> Unit,
    private val favoriteSwitch: (Product) -> Unit
) :
    ListAdapter<Product, ProductsAdapter.StoreMerchandiseViewHolder>(
        ProductsDiffCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreMerchandiseViewHolder =
        StoreMerchandiseViewHolder.from(parent)

    override fun onBindViewHolder(holder: StoreMerchandiseViewHolder, position: Int) {
        val merchandiseItem = getItem(position)
        holder.bind(merchandiseItem, onItemClicked, favoriteSwitch)
    }

    class StoreMerchandiseViewHolder(private val binding: ProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            product: Product,
            onItemClicked: (String) -> Unit,
            favoriteSwitch: (Product) -> Unit
        ) {
            binding.product = product
            binding.thumbnail.setOnClickListener { onItemClicked(product.model) }
            binding.switchFavorite = { favoriteSwitch(product) }
        }

        companion object {
            fun from(parent: ViewGroup): StoreMerchandiseViewHolder {
                val binding =
                    ProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return StoreMerchandiseViewHolder(binding)
            }
        }
    }
}

class ProductsDiffCallBack : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.model == newItem.model

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem == newItem
}

