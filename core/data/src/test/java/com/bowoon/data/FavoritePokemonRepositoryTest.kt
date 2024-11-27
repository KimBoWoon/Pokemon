package com.bowoon.data

import com.bowoon.data.repository.FavoritePokemonRepositoryImpl
import com.bowoon.model.Pokemon
import com.bowoon.testing.MainDispatcherRule
import com.bowoon.testing.TestUserDataRepository
import com.bowoon.testing.emptyUserData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

class CompositeUserNewsResourceRepositoryTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val userDataRepository = TestUserDataRepository()

    private val userNewsResourceRepository = FavoritePokemonRepositoryImpl(
        userDataRepository = userDataRepository,
    )

    @Test
    fun whenNoFilters_allFavoritePokemonAreReturned() = runTest {
        val userNewsResources = userNewsResourceRepository.observeAllFavoritePokemon()
        val userData = emptyUserData.copy(
            favoriteList = samplePokemonList
        )

        userDataRepository.setUserData(userData)

        assertEquals(
            samplePokemonList,
            userNewsResources.first(),
        )
    }
}

private val samplePokemonList = listOf(
    Pokemon(
        name = "name1",
        url = "url1"
    ),
    Pokemon(
        name = "name2",
        url = "url2"
    ),
    Pokemon(
        name = "name3",
        url = "url3"
    )
)