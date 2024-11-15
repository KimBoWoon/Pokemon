package com.bowoon.data.di

import com.bowoon.data.repository.PokemonRepository
import com.bowoon.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindPokemonRepository(
        repository: PokemonRepositoryImpl
    ): PokemonRepository
}