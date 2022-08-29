package com.piroworkz.architectcoders.app.ui.favorites

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.store.StoreFragmentDirections

class FavoritesState(
    private val menuHost: MenuHost,
    private val navController: NavController
) {

    fun navigate(model: String) {
        navController.apply {
            popBackStack(R.id.userFavoritesFragment, false)
            navigate(StoreFragmentDirections.actionToDetailFragment(model))
        }
    }
}

fun FavoritesFragment.buildState(
    menuHost: MenuHost = requireActivity(),
    navController: NavController = findNavController()
) = FavoritesState(menuHost, navController)