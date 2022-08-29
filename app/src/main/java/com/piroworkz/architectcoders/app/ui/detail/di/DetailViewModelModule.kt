package com.piroworkz.architectcoders.app.ui.detail.di

import androidx.lifecycle.SavedStateHandle
import com.piroworkz.architectcoders.app.di.DetailFragmentProductModel
import com.piroworkz.architectcoders.app.ui.detail.fragment.DetailFragmentArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @DetailFragmentProductModel
    fun provideProductModel(savedStateHandle: SavedStateHandle): String =
        DetailFragmentArgs.fromSavedStateHandle(savedStateHandle).model
}