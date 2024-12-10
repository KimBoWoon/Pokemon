package com.bowoon.pokemon

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.testing.invoke
import com.bowoon.model.PokemonStatus
import com.bowoon.network.PokemonApis
import com.bowoon.pokemon.navigation.PokemonRoute
import com.bowoon.testing.MainDispatcherRule
import com.bowoon.testing.TestPokemonRepository
import com.bowoon.testing.pokemonAbility
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import kotlin.test.assertEquals
import kotlin.test.assertIs

/**
 * 테스트를 위한 주소
 * 실제 주소인지는 중요하지 않음
 */
private const val BASE_URL = "https://localhost/"

@RunWith(RobolectricTestRunner::class)
class PokemonVMTest {
    /**
     * 테스트 환경설정
     */
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val pokemonRepository = TestPokemonRepository()
    private var savedStateHandle = SavedStateHandle(
        route = PokemonRoute(url = BASE_URL)
    )
    private lateinit var viewModel: PokemonVM
    private lateinit var server: MockWebServer
    private lateinit var pokemonApi: PokemonApis
    private lateinit var retrofit: Retrofit

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
        retrofit = Retrofit.Builder()
            .baseUrl(server.url(BASE_URL))
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
        pokemonApi = retrofit.create(PokemonApis::class.java)
        viewModel = PokemonVM(
            savedStateHandle = savedStateHandle,
            repository = pokemonRepository
        )
    }

    @After
    fun cleanUp() {
        server.shutdown()
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        assertEquals(PokemonUiState.Loading, viewModel.pokemonUiState.value)
    }

    @Test
    fun stateIsSuccessAfterPokemonInfoLoaded() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.pokemonUiState.collect() }

        pokemonRepository.setPokemonInfo(pokemonAbility)
        val item = viewModel.pokemonUiState.value
        assertIs<PokemonUiState.Success>(item)

        val response = PokemonStatus().toString()
        server.enqueue(MockResponse().setBody(response))
        val result = pokemonRepository.getPokemonInfo(BASE_URL).first()

        assertEquals(result, item.data)
    }
}