package com.bowoon.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.bowoon.data.repository.UserDataRepository
import com.bowoon.data.repository.UserDataRepositoryImpl
import com.bowoon.datastore.PokemonDataSource
import com.bowoon.model.DarkThemeConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
class UserDataRepositoryTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())
    private lateinit var subject: UserDataRepository
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
        subject = UserDataRepositoryImpl(datastore = userDatastore)
    }

    @Test
    fun set_dark_mode() = testScope.runTest {
        subject.updateDarkModeTheme(DarkThemeConfig.DARK)

        assertEquals(DarkThemeConfig.DARK, subject.userData.map { it.isDarkMode }.first())
        assertEquals(DarkThemeConfig.DARK, userDatastore.userData.map { it.isDarkMode }.first())
    }
}