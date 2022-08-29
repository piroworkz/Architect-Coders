package com.piroworkz.architectcoders.app.ui.store.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.common.circularProgressDrawable
import com.piroworkz.architectcoders.domain.Banner
import com.piroworkz.architectcoders.domain.Product

@BindingAdapter("setUpProducts")
fun RecyclerView.setUpProducts(products: List<Product>?) {
    products?.let {
        (adapter as ProductsAdapter).submitList(it)
    }
}

@BindingAdapter("setUpBanners")
fun RecyclerView.setUpBanners(banners: List<Banner>?) {
    banners?.let {
        (adapter as BannersAdapter).submitList(it.shuffled())
    }
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .placeholder(circularProgressDrawable(context))
            .into(this)
    }
}

@BindingAdapter("setPrice")
fun TextView.setPrice(price: Int?) {
    price?.let {
        text = context.resources.getString(R.string.price, price.toString())
    }
}

@BindingAdapter("size", "product", "pieces")
fun TextView.availablePieces(size: com.piroworkz.architectcoders.domain.Size?, product: Product?, pieces: Int) {
    text = when (size) {
        com.piroworkz.architectcoders.domain.Size.SMALL -> context.getString(
            R.string.available_pieces,
            product?.smallSize?.minus(pieces).toString()
        )
        com.piroworkz.architectcoders.domain.Size.MEDIUM -> context.getString(
            R.string.available_pieces,
            product?.mediumSize?.minus(pieces).toString()
        )
        com.piroworkz.architectcoders.domain.Size.LARGE -> context.getString(
            R.string.available_pieces,
            product?.largeSize?.minus(pieces).toString()
        )
        else -> {
            context.getString(R.string.enter_quantity)
        }
    }
}