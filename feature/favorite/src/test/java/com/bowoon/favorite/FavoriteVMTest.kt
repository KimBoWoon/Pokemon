package com.bowoon.favorite

import com.bowoon.data.repository.FavoritePokemonRepositoryImpl
import com.bowoon.domain.GetFavoritePokemonUseCase
import com.bowoon.model.Pokemon
import com.bowoon.testing.MainDispatcherRule
import com.bowoon.testing.TestUserDataRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class FavoriteVMTest {
    /**
     * 테스트 환경설정
     */
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val testUserDataRepository = TestUserDataRepository()
    private val favoritePokemonRepository = FavoritePokemonRepositoryImpl(
        userDataRepository = testUserDataRepository
    )
    private val getFavoritePokemonUseCase = GetFavoritePokemonUseCase(
        userDataRepository = testUserDataRepository
    )
    private lateinit var viewModel: FavoriteVM

    @Before
    fun setup() {
        viewModel = FavoriteVM(
            repository = favoritePokemonRepository,
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