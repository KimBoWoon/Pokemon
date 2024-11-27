package com.bowoon.testing

import com.bowoon.data.repository.PokemonRepository
import com.bowoon.model.PokemonAbility
import com.bowoon.model.PokemonList
import com.bowoon.network.model.Ability
import com.bowoon.network.model.AbilityInfo
import com.bowoon.network.model.AbilityResponse
import com.bowoon.network.model.Cries
import com.bowoon.network.model.Form
import com.bowoon.network.model.GameIndice
import com.bowoon.network.model.Item
import com.bowoon.network.model.MoveInfo
import com.bowoon.network.model.OfficialArtwork
import com.bowoon.network.model.Other
import com.bowoon.network.model.PokemonHeldItem
import com.bowoon.network.model.PokemonListResponse
import com.bowoon.network.model.PokemonResponse
import com.bowoon.network.model.Species
import com.bowoon.network.model.Sprites
import com.bowoon.network.model.Stat
import com.bowoon.network.model.StatInfo
import com.bowoon.network.model.Type
import com.bowoon.network.model.TypeInfo
import com.bowoon.network.model.Version
import com.bowoon.network.model.asExternalModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TestPokemonRepository : PokemonRepository {
    private val pokemonListFlow: MutableSharedFlow<PokemonListResponse> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val pokemonFlow: MutableSharedFlow<AbilityResponse> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override suspend fun getPokemonList(url: String): PokemonList = pokemonListFlow.map { it.asExternalModel() }.first()

    override fun getPokemonInfo(url: String): Flow<PokemonAbility> = pokemonFlow.map { it.asExternalModel() }

    fun setPokemonList(list: PokemonListResponse) {
        pokemonListFlow.tryEmit(list)
    }

    /**
     * 테스트를 위한 데이터 설정
     */
    fun setPokemonInfo(ability: AbilityResponse) {
        pokemonFlow.tryEmit(ability)
    }
}

val pokemonList = PokemonListResponse(
    count = 5,
    next = "",
    previous = null,
    results = listOf(
        PokemonResponse(name = "name1", url = "rul1"),
        PokemonResponse(name = "name2", url = "rul2"),
        PokemonResponse(name = "name3", url = "rul3"),
        PokemonResponse(name = "name4", url = "rul4"),
        PokemonResponse(name = "name5", url = "rul5")
    )
)

val pokemonAbility = AbilityResponse(
    abilities = listOf(
        Ability(
            ability = AbilityInfo(name = "AbilityName1", url = "AbilityUrl1"),
            isHidden = false,
            slot = 1
        ),
        Ability(
            ability = AbilityInfo(name = "AbilityName2", url = "AbilityUrl2"),
            isHidden = true,
            slot = 2
        )
    ),
    baseExperience = 39,
    cries = Cries(latest = "latest", legacy = "legacy"),
    forms = listOf(
        Form(name = "FormsName1", url = "FormsUrl1"),
        Form(name = "FormsName2", url = "FormsUrl2")
    ),
    gameIndices = listOf(
        GameIndice(gameIndex = 20, version = Version(name = "VersionName1", url = "VersionUrl1")),
        GameIndice(gameIndex = 10, version = Version(name = "VersionName2", url = "VersionUrl2"))
    ),
    height = 50,
    heldItems = listOf(
        PokemonHeldItem(item = Item(name = "ItemName1", url = "ItemUrl1")),
        PokemonHeldItem(item = Item(name = "ItemName2", url = "ItemUrl2"))
    ),
    id = 1,
    isDefault = true,
    locationAreaEncounters = "LocationAreaEncounters",
    moves = listOf(
        MoveInfo(name = "MoveInfo1", url = "MoveUrl1"),
        MoveInfo(name = "MoveInfo2", url = "MoveUrl2")
    ),
    name = "PokemonAbilityName",
    order = 0,
    species = Species(name = "SpeciesName1", url = "SpeciesUrl1"),
    sprites = Sprites(
        backDefault = "BackDefault",
        backFemale = "BackFemale",
        backShiny = "BackShiny",
        backShinyFemale = "BackShinyFemale",
        frontDefault = "FrontDefault",
        frontFemale = "FrontFemale",
        frontShiny = "FrontShiny",
        frontShinyFemale = "FrontShinyFemale",
        other = Other(
            dreamWorld = Sprites(
                backDefault = "BackDefault",
                backFemale = "BackFemale",
                backShiny = "BackShiny",
                backShinyFemale = "BackShinyFemale",
                frontDefault = "FrontDefault",
                frontFemale = "FrontFemale",
                frontShiny = "FrontShiny",
                frontShinyFemale = "FrontShinyFemale",
                other = null
            ),
            home = Sprites(
                backDefault = "BackDefault",
                backFemale = "BackFemale",
                backShiny = "BackShiny",
                backShinyFemale = "BackShinyFemale",
                frontDefault = "FrontDefault",
                frontFemale = "FrontFemale",
                frontShiny = "FrontShiny",
                frontShinyFemale = "FrontShinyFemale",
                other = null
            ),
            officialArtwork = OfficialArtwork(
                frontDefault = "FrontDefault",
                frontShiny = "FrontShiny"
            ),
            showdown = Sprites(
                backDefault = "BackDefault",
                backFemale = "BackFemale",
                backShiny = "BackShiny",
                backShinyFemale = "BackShinyFemale",
                frontDefault = "FrontDefault",
                frontFemale = "FrontFemale",
                frontShiny = "FrontShiny",
                frontShinyFemale = "FrontShinyFemale",
                other = null
            )
        )
    ),
    stats = listOf(
        Stat(
            baseStat = 1,
            effort = 2,
            stat = StatInfo(name = "StatInfoName1", url = "StatInfoUrl1")
        ),
        Stat(
            baseStat = 3,
            effort = 4,
            stat = StatInfo(name = "StatInfoName2", url = "StatInfoUrl2")
        ),
        Stat(
            baseStat = 5,
            effort = 6,
            stat = StatInfo(name = "StatInfoName3", url = "StatInfoUrl3")
        )
    ),
    types = listOf(
        Type(slot = 1, type = TypeInfo(name = "TypeInfoName1", url = "TypeInfoUrl1")),
        Type(slot = 2, type = TypeInfo(name = "TypeInfoName2", url = "TypeInfoUrl2")),
        Type(slot = 3, type = TypeInfo(name = "TypeInfoName3", url = "TypeInfoUrl3")),
        Type(slot = 4, type = TypeInfo(name = "TypeInfoName4", url = "TypeInfoUrl4"))
    ),
    weight = 305
)