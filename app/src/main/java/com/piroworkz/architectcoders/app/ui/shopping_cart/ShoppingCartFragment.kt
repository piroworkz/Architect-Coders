package com.piroworkz.architectcoders.app.ui.shopping_cart

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.app.ui.shopping_cart.ShoppingCartViewModel.State
import com.piroworkz.architectcoders.databinding.FragmentShoppingCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartFragment : Fragment(R.layout.fragment_shopping_cart) {

    private lateinit var state: ShoppingCartState
    private lateinit var binding: FragmentShoppingCartBinding
    private val cartAdapter = ShoppingCartAdapter(
        onDeleteClicked = { viewModel.removeFromShoppingCart(it) },
        onItemClicked = { state.navigate(it) }
    )
    private val viewModel: ShoppingCartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentShoppingCartBinding.bind(view).apply {
            shoppingCartRecyclerView.run { adapter = cartAdapter }
            toolbar.apply {
                setupWithNavController(findNavController())
                setOnMenuItemClickListener(::emptyShoppingCart)
            }
        }
        state = buildState()

        viewLifecycleOwner.collectFlow(viewModel.state) {
            viewModel.getPurchaseTotal(it.shoppingCart)
            configDataBindingVariables(it)
        }
    }

    private fun emptyShoppingCart(menuItem: MenuItem): Boolean {
        viewModel.emptyShoppingCart()
        return menuItem.isVisible
    }

    private fun configDataBindingVariables(state: State) = binding.run {
        shoppingCart = state.shoppingCart
        total = state.purchaseTotal
    }
}