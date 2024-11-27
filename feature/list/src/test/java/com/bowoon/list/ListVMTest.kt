package com.bowoon.list

import com.bowoon.data.repository.FavoritePokemonRepositoryImpl
import com.bowoon.domain.GetFavoritePokemonUseCase
import com.bowoon.model.Pokemon
import com.bowoon.testing.MainDispatcherRule
import com.bowoon.testing.TestPokemonRepository
import com.bowoon.testing.TestUserDataRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class ListVMTest {
    /**
     * 테스트 환경설정
     */
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val testPokemonRepository = TestPokemonRepository()
    private val testUserDataRepository = TestUserDataRepository()
    private val favoritePokemonRepository = FavoritePokemonRepositoryImpl(
        userDataRepository = testUserDataRepository
    )
    private val getFavoritePokemonUseCase = GetFavoritePokemonUseCase(
        userDataRepository = testUserDataRepository
    )
    private lateinit var viewModel: ListVM

    @Before
    fun setup() {
        viewModel = ListVM(
            pokemonRepository = testPokemonRepository,
            favoritePokemonRepository = favoritePokemonRepository,
            getFavoritePokemonUseCase = getFavoritePokemonUseCase
        )
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        assertEquals(FavoritePokemonUiState.Loading, viewModel.favoritePokemonUiState.value)
    }

    @Test
    fun stateIsSuccessAfterFavoritePokemonLoaded() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.favoritePokemonUiState.collect() }

        testUserDataRepository.updateFavoritePokemon(Pokemon("name", "url"))

        assertEquals(
            FavoritePokemonUiState.Success(listOf(Pokemon("name", "url"))),
            viewModel.favoritePokemonUiState.value
        )
    }
}