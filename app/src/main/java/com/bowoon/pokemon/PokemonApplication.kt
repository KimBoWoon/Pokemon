package com.bowoon.pokemon

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class PokemonApplication : Application(), SingletonImageLoader.Factory {
    @Inject
    lateinit var imageLoader: dagger.Lazy<ImageLoader>

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader = imageLoader.get()
}