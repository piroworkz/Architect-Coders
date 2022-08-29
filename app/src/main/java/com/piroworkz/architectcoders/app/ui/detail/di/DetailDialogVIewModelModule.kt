package com.piroworkz.architectcoders.app.ui.detail.di

import androidx.lifecycle.SavedStateHandle
import com.piroworkz.architectcoders.app.di.DetailDialogProductModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailDialogVIewModelModule {

    @Provides
    @ViewModelScoped
    @DetailDialogProductModel
    fun provideDialogProductModel(savedStateHandle: SavedStateHandle): String? =
        savedStateHandle.get<String>("model")
}