package com.bowoon.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.bowoon.model.DarkThemeConfig
import com.bowoon.model.Pokemon
import com.bowoon.testing.MainDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
class PokemonDataSourceTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private lateinit var userDatastore: PokemonDataSource
    private val json = Json {}
    private lateinit var testDataStore: DataStore<Preferences>

    @Before
    fun setup() {
        val testContext: Context = ApplicationProvider.getApplicationContext<Context>()
        testDataStore = PreferenceDataStoreFactory.create(
            produceFile = { testContext.preferencesDataStoreFile("TEST_DATASTORE") }
        )
        userDatastore = PokemonDataSource(testDataStore, json)
    }

    @After
    fun cleanUp() {
        runTest { testDataStore.edit { it.clear() } }
    }

    @Test
    fun set_dark_mode_test() = runTest {
        userDatastore.updateDarkTheme(DarkThemeConfig.DARK)
        assertEquals(
            DarkThemeConfig.DARK,
            userDatastore.userData.map { it.isDarkMode }.first()
        )
    }

    @Test
    fun set_favorite_pokemon_test() = runTest {
        val pokemon = Pokemon(name = "name1", url = "url1")

        userDatastore.updateFavoritePokemon(pokemon)

        assertEquals(
            listOf(pokemon),
            userDatastore.userData.map { it.favoriteList }.first()
        )
    }
}