package com.bowoon.domain

import com.bowoon.model.Pokemon
import com.bowoon.testing.MainDispatcherRule
import com.bowoon.testing.TestUserDataRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class GetFavoritePokemonUseCaseTest {
    /**
     * 테스트 환경설정
     */
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val testUserDataRepository = TestUserDataRepository()
    private val getFavoritePokemonUseCase = GetFavoritePokemonUseCase(
        userDataRepository = testUserDataRepository
    )

    @Test
    fun get_favorite_pokemon_list_test() = runTest {
        val favoritePokemonList = getFavoritePokemonUseCase()

        testUserDataRepository.updateFavoritePokemon(Pokemon("name", "url"))

        assertEquals(
            listOf(Pokemon("name", "url")),
            favoritePokemonList.first()
        )
    }
}