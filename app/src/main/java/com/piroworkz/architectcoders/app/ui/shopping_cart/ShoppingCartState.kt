package com.piroworkz.architectcoders.app.ui.shopping_cart

import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.piroworkz.architectcoders.app.ui.store.StoreFragmentDirections

class ShoppingCartState(private val navController: NavController) {
    fun navigate(model: String) =
        navController.navigate(StoreFragmentDirections.actionToDetailFragment(model))
}

fun ShoppingCartFragment.buildState(): ShoppingCartState {
    return ShoppingCartState(findNavController())
}