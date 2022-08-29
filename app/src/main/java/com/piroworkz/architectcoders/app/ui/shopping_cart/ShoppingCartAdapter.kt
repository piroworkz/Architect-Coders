package com.piroworkz.architectcoders.app.ui.shopping_cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.piroworkz.architectcoders.databinding.ShoppingCartItemBinding
import com.piroworkz.architectcoders.domain.CartItem

class ShoppingCartAdapter(
    private val onDeleteClicked: (com.piroworkz.architectcoders.domain.CartItem) -> Unit,
    private val onItemClicked: (String) -> Unit
) : ListAdapter<com.piroworkz.architectcoders.domain.CartItem, ShoppingCartViewHolder>(ShoppingCartDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder =
        ShoppingCartViewHolder.from(parent)

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) =
        holder.bind(getItem(position), onDeleteClicked, onItemClicked)
}

class ShoppingCartViewHolder(private val binding: ShoppingCartItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: com.piroworkz.architectcoders.domain.CartItem,
        onDeleteClicked: (com.piroworkz.architectcoders.domain.CartItem) -> Unit,
        onItemClicked: (String) -> Unit
    ) {
        val sum = item.smallSize + item.mediumSize + item.largeSize
        binding.run {
            total = (sum * item.price).toString()
            totalPieces = sum.toString()
            sizeTextView.setText(item)
            cartItem = item
            delete = { onDeleteClicked(item) }
            modelDetail = { onItemClicked(item.model) }
        }
    }

    companion object {
        fun from(parent: ViewGroup): ShoppingCartViewHolder {
            val binding = ShoppingCartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShoppingCartViewHolder(binding)
        }
    }
}

class ShoppingCartDiffCallBack : DiffUtil.ItemCallback<com.piroworkz.architectcoders.domain.CartItem>() {
    override fun areItemsTheSame(oldItem: com.piroworkz.architectcoders.domain.CartItem, newItem: com.piroworkz.architectcoders.domain.CartItem): Boolean =
        oldItem.model == newItem.model

    override fun areContentsTheSame(oldItem: com.piroworkz.architectcoders.domain.CartItem, newItem: com.piroworkz.architectcoders.domain.CartItem): Boolean =
        oldItem == newItem
}