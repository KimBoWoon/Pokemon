package com.bowoon.data.di

import com.bowoon.data.repository.FavoritePokemonRepository
import com.bowoon.data.repository.FavoritePokemonRepositoryImpl
import com.bowoon.data.repository.PokemonRepository
import com.bowoon.data.repository.PokemonRepositoryImpl
import com.bowoon.data.repository.UserDataRepository
import com.bowoon.data.repository.UserDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModules {
    @Binds
    @ViewModelScoped
    abstract fun bindPokemonRepository(
        repository: PokemonRepositoryImpl
    ): PokemonRepository

    @Binds
    @ViewModelScoped
    abstract fun bindFavoriteRepository(
        repository: FavoritePokemonRepositoryImpl
    ): FavoritePokemonRepository

    @Binds
    @ViewModelScoped
    abstract fun bindUserRepository(
        repository: UserDataRepositoryImpl
    ): UserDataRepository
}