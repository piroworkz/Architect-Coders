package com.piroworkz.architectcoders.app.ui.store

import android.Manifest
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.piroworkz.architectcoders.app.ui.store.utils.LocationPermissionRequester
import com.piroworkz.architectcoders.app.ui.menu.MenuDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class StoreState(
    private val navController: NavController,
    private val scope: CoroutineScope,
    private val locationPermissionRequester: LocationPermissionRequester,
) {

    fun requestLocationPermission(onResult: (Boolean) -> Unit) {
        scope.launch { onResult(locationPermissionRequester.request()) }
    }

    fun navigateToDetail(model: String) {
        val action = StoreFragmentDirections.actionToDetailFragment(model)
        navController.navigate(action)
    }

    fun openMenuDialog(manager: FragmentManager) {
        val dialog = MenuDialog()
        if (!dialog.isAdded) dialog.show(manager, MenuDialog.TAG)
    }

}

fun StoreFragment.buildState(
    navController: NavController = findNavController(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    locationPermissionRequester: LocationPermissionRequester = LocationPermissionRequester(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ),
) = StoreState(navController, scope, locationPermissionRequester)