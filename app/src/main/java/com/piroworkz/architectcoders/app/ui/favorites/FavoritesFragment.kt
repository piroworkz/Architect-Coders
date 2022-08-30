package com.piroworkz.architectcoders.app.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.databinding.FragmentFavoritesBinding
import com.piroworkz.piroworkzmvvm.app.ui.favorites.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var state: FavoritesState

    private val viewModel: FavoritesViewModel by viewModels()

    private val adapter = FavoritesAdapter(
        listener = {product -> viewModel.switchFavorite(product) },
        onItemCLicked = { state.navigate(it) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesBinding.bind(view).apply {
            toolbar.apply {->
                setupWithNavController(findNavController())
                setOnMenuItemClickListener { onActionMenu() }
            }
        }
        state = buildState()
        viewLifecycleOwner.collectFlow(viewModel.state, body = ::configDataBindingVariables)
    }

    private fun configDataBindingVariables(state: FavoritesViewModel.State) =
        binding.run {
            products = state.favorites
            favoritesRecyclerView.adapter = adapter
        }

    private fun onActionMenu(): Boolean {
        viewModel.emptyFavorite()
        return true
    }
}