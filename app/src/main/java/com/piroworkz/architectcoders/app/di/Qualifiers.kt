package com.piroworkz.architectcoders.app.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GeoBaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DatabaseName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GeoUserName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DetailFragmentProductModel

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DetailDialogProductModel