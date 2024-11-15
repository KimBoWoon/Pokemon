package com.bowoon.imgloader.di

import android.content.Context
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.crossfade
import com.bowoon.imgloader.ImgLoader.Companion.CACHE_BYTES_SIZE
import com.bowoon.imgloader.ImgLoader.Companion.CACHE_FOLDER_NAME
import com.bowoon.imgloader.ImgLoader.Companion.MEMORY_CACHE_PERCENT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImgLoaderModule {
    @Provides
    @Singleton
    fun imageLoader(
        @ApplicationContext context: Context,
    ): ImageLoader = ImageLoader.Builder(context)
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
//        .respectCacheHeaders(false)
        .build()
}