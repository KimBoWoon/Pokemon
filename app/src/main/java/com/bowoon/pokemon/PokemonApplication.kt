package com.bowoon.pokemon

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.crossfade
import com.bowoon.pokemon.imgLoader.ImgLoader.Companion.CACHE_BYTES_SIZE
import com.bowoon.pokemon.imgLoader.ImgLoader.Companion.CACHE_FOLDER_NAME
import com.bowoon.pokemon.imgLoader.ImgLoader.Companion.MEMORY_CACHE_PERCENT
import dagger.hilt.android.HiltAndroidApp
import java.io.File

@HiltAndroidApp
class PokemonApplication : Application(), SingletonImageLoader.Factory {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context, MEMORY_CACHE_PERCENT)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(File(context.externalCacheDir, CACHE_FOLDER_NAME))
                    .maxSizeBytes(CACHE_BYTES_SIZE)
                    .build()
            }
            .crossfade(true)
//            .respectCacheHeaders(false)
            .build()
    }
}