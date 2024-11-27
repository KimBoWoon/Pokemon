package com.bowoon.data

import com.bowoon.network.PokemonApis
import com.bowoon.network.model.asExternalModel
import com.bowoon.testing.MainDispatcherRule
import com.bowoon.testing.TestPokemonRepository
import com.bowoon.testing.pokemonAbility
import com.bowoon.testing.pokemonList
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * 테스트를 위한 주소
 * 실제 주소인지는 중요하지 않음
 */
private const val BASE_URL = "https://localhost/"

class PokemonRepositoryTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val testPokemonRepository = TestPokemonRepository()
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
    }

    @After
    fun cleanUp() {
        server.shutdown()
    }

    @Test
    fun get_pokemon_list() = runTest {
        testPokemonRepository.setPokemonList(pokemonList)

        server.enqueue(MockResponse().setBody(pokemonList.toString()))
        val result = testPokemonRepository.getPokemonList(BASE_URL)

        assertEquals(result, pokemonList.asExternalModel())
    }

    @Test
    fun get_pokemon_info() = runTest {
        testPokemonRepository.setPokemonInfo(pokemonAbility)

        server.enqueue(MockResponse().setBody(pokemonAbility.toString()))
        val result = testPokemonRepository.getPokemonInfo(BASE_URL).first()

        assertEquals(result, pokemonAbility.asExternalModel())
    }
}