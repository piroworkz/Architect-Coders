package com.piroworkz.architectcoders.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.piroworkz.architectcoders.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentContainer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)
    }
}