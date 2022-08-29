package com.piroworkz.architectcoders.app.ui.menu

import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.piroworkz.architectcoders.R

class MenuState(
    private val navController: NavController
) {
    fun navigate(menuItem: MenuItem, signOut: () -> Unit) {
        if (menuItem.itemId == R.id.signOutButton) {
            signOut()
        } else {
            navController.apply {
                popBackStack(R.id.menuBottomSheetFragment, true)
                navigate(menuItem.itemId)
            }
        }
    }
}

fun MenuDialog.buildState(navController: NavController = findNavController()) =
    MenuState(navController)