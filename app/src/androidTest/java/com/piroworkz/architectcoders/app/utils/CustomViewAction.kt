package com.piroworkz.architectcoders.app.utils

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction


object CustomViewAction {
    fun clickChildViewWithId(id: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription(): String {
            return "Click on a child view with specified id."
        }

        override fun perform(uiController: UiController?, view: View) {
            val v: View = view.findViewById(id)
            v.performClick()
        }
    }
}