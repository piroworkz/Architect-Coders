package com.piroworkz.architectcoders.app.ui.store.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.piroworkz.architectcoders.databinding.BannerBinding
import com.piroworkz.architectcoders.domain.Banner

class BannersAdapter :
    ListAdapter<com.piroworkz.architectcoders.domain.Banner, BannersAdapter.StoreBannersViewHolder>(BannersDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreBannersViewHolder =
        StoreBannersViewHolder.from(parent)

    override fun onBindViewHolder(holder: StoreBannersViewHolder, position: Int) =
        holder.bind(getItem(position))

    class StoreBannersViewHolder(private val binding: BannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(banner: com.piroworkz.architectcoders.domain.Banner) {
            binding.banner = banner
        }


        companion object {
            fun from(parent: ViewGroup): StoreBannersViewHolder {
                val binding =
                    BannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return StoreBannersViewHolder(binding)
            }
        }
    }
}

class BannersDiffCallBack : DiffUtil.ItemCallback<com.piroworkz.architectcoders.domain.Banner>() {
    override fun areItemsTheSame(oldItem: com.piroworkz.architectcoders.domain.Banner, newItem: com.piroworkz.architectcoders.domain.Banner): Boolean =
        oldItem.model == newItem.model

    override fun areContentsTheSame(oldItem: com.piroworkz.architectcoders.domain.Banner, newItem: com.piroworkz.architectcoders.domain.Banner): Boolean =
        oldItem == newItem
}
