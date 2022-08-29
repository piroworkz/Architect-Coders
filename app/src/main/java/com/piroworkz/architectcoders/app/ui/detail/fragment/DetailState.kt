package com.piroworkz.architectcoders.app.ui.detail.fragment

import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.piroworkz.architectcoders.R
import com.piroworkz.architectcoders.app.ui.detail.dialog.DetailDialog


class DetailState(
    private val model: String,
    private val navController: NavController,
    private val menuHost: MenuHost,
) {

    fun openDialog(
        manager: FragmentManager,
    ) {
        val dialog = DetailDialog()
        dialog.arguments = bundleOf("model" to model)
        if (!dialog.isAdded) dialog.show(manager, DetailDialog.TAG)
    }

    fun navigate(destination: Int) {
        navController.apply {
            popBackStack(R.id.detailStoreFragment, false)
            navigate(destination)
        }
    }
}

fun DetailFragment.buildState(
    model: String,
    navController: NavController = findNavController(),
    menuHost: MenuHost = requireActivity()
): DetailState = DetailState(model, navController, menuHost)
