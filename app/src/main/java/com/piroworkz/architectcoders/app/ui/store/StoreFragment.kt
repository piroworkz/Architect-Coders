package com.piroworkz.architectcoders.app.ui.store

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.common.collectFlow
import com.piroworkz.architectcoders.app.ui.store.StoreViewModel.State
import com.piroworkz.architectcoders.app.ui.store.utils.BannersAdapter
import com.piroworkz.architectcoders.app.ui.store.utils.BannersLayoutManager
import com.piroworkz.architectcoders.app.ui.store.utils.ProductsAdapter
import com.piroworkz.architectcoders.databinding.FragmentMainStoreBinding
import com.piroworkz.piroworkzmvvm.app.ui.store.utils.ProductsLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : Fragment(R.layout.fragment_main_store) {

    private val viewModel: StoreViewModel by viewModels()
    private lateinit var state: StoreState
    private lateinit var binding: FragmentMainStoreBinding

    private val bannersAdapter = BannersAdapter()

    private val productsAdapter = ProductsAdapter(
        onItemClicked = { state.navigateToDetail(it) },
        favoriteSwitch = { product -> viewModel.switchFavorite(product) }
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        state = buildState().apply {
            requestLocationPermission { viewModel.onPermissionRequestResult() }
        }
        binding = FragmentMainStoreBinding.bind(view).apply {
            configBannersRecyclerView(
                BannersLayoutManager(
                    context = requireContext(),
                    recyclerView = bannersRecyclerView
                )
            )
            configProductsRecyclerView()
        }
        viewLifecycleOwner.collectFlow(viewModel.state, body = this::configDataBindingVariables)
    }

    private fun configDataBindingVariables(state: State) {
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            products = state.products
            banners = state.banners
            openDialog = { this@StoreFragment.state.openMenuDialog(childFragmentManager) }
        }
    }

    private fun FragmentMainStoreBinding.configBannersRecyclerView(manager: BannersLayoutManager) {
        bannersRecyclerView.run {
            PagerSnapHelper().attachToRecyclerView(this)
            adapter = bannersAdapter
            layoutManager = manager
            manager.autoScroll(2500)
        }
    }

    private fun FragmentMainStoreBinding.configProductsRecyclerView() {
        productsRecyclerView.run {
            adapter = productsAdapter
            layoutManager = ProductsLayoutManager(requireContext())
        }
    }
}
