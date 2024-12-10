package com.bowoon.testing

import com.bowoon.data.repository.PokemonRepository
import com.bowoon.model.Evolution
import com.bowoon.model.PokemonStatus
import com.bowoon.network.model.EvolutionResponse
import com.bowoon.network.model.NamedAPIResource
import com.bowoon.network.model.OfficialArtwork
import com.bowoon.network.model.Other
import com.bowoon.network.model.PokemonCries
import com.bowoon.network.model.PokemonHeldItem
import com.bowoon.network.model.PokemonListResponse
import com.bowoon.network.model.PokemonMove
import com.bowoon.network.model.PokemonMoveVersion
import com.bowoon.network.model.PokemonResponse
import com.bowoon.network.model.PokemonSprites
import com.bowoon.network.model.PokemonStat
import com.bowoon.network.model.PokemonStatusResponse
import com.bowoon.network.model.PokemonType
import com.bowoon.network.model.VersionGameIndex
import com.bowoon.network.model.asExternalModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class TestPokemonRepository : PokemonRepository {
    private val pokemonListFlow: MutableSharedFlow<PokemonListResponse> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val pokemonFlow: MutableSharedFlow<PokemonStatusResponse> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val pokemonEvolutionFlow: MutableSharedFlow<EvolutionResponse> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getPokemonInfo(url: String): Flow<PokemonStatus> = pokemonFlow.map { it.asExternalModel() }

    override fun getPokemonEvolution(url: String): Flow<Evolution> = pokemonEvolutionFlow.map { it.asExternalModel() }

    fun setPokemonList(list: PokemonListResponse) {
        pokemonListFlow.tryEmit(list)
    }

    /**
     * 테스트를 위한 데이터 설정
     */
    fun setPokemonInfo(ability: PokemonStatusResponse) {
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

val pokemonAbility = PokemonStatusResponse(
    abilities = listOf(
        com.bowoon.network.model.PokemonAbility(
            ability = NamedAPIResource(name = "AbilityName1", url = "AbilityUrl1"),
            isHidden = false,
            slot = 1
        ),
        com.bowoon.network.model.PokemonAbility(
            ability = NamedAPIResource(name = "AbilityName2", url = "AbilityUrl2"),
            isHidden = true,
            slot = 2
        )
    ),
    baseExperience = 39,
    cries = PokemonCries(latest = "latest", legacy = "legacy"),
    forms = listOf(
        NamedAPIResource(name = "FormsName1", url = "FormsUrl1"),
        NamedAPIResource(name = "FormsName2", url = "FormsUrl2")
    ),
    gameIndices = listOf(
        VersionGameIndex(gameIndex = 20, version = NamedAPIResource(name = "VersionName1", url = "VersionUrl1")),
        VersionGameIndex(gameIndex = 10, version = NamedAPIResource(name = "VersionName2", url = "VersionUrl2"))
    ),
    height = 50,
    heldItems = listOf(
        PokemonHeldItem(item = NamedAPIResource(name = "ItemName1", url = "ItemUrl1")),
        PokemonHeldItem(item = NamedAPIResource(name = "ItemName2", url = "ItemUrl2"))
    ),
    id = 1,
    isDefault = true,
    locationAreaEncounters = "LocationAreaEncounters",
    moves = listOf(
        PokemonMove(
            NamedAPIResource(name = "MoveInfo1", url = "MoveUrl1"),
            listOf(
                PokemonMoveVersion(
                    levelLearnedAt = 1,
                    moveLearnMethod = NamedAPIResource(name = "MoveLearnMethodName1", url = "MoveLearnMethodUrl1"),
                    versionGroup = NamedAPIResource(name = "VersionGroupName1", url = "VersionGroupUrl1")
                )
            )
        ),
        PokemonMove(
            NamedAPIResource(name = "MoveInfo2", url = "MoveUrl2"),
            listOf(
                PokemonMoveVersion(
                    levelLearnedAt = 1,
                    moveLearnMethod = NamedAPIResource(name = "MoveLearnMethodName1", url = "MoveLearnMethodUrl1"),
                    versionGroup = NamedAPIResource(name = "VersionGroupName1", url = "VersionGroupUrl1")
                )
            )
        )
    ),
    name = "PokemonAbilityName",
    order = 0,
    species = NamedAPIResource(name = "SpeciesName1", url = "SpeciesUrl1"),
    sprites = PokemonSprites(
        backDefault = "BackDefault",
        backFemale = "BackFemale",
        backShiny = "BackShiny",
        backShinyFemale = "BackShinyFemale",
        frontDefault = "FrontDefault",
        frontFemale = "FrontFemale",
        frontShiny = "FrontShiny",
        frontShinyFemale = "FrontShinyFemale",
        other = Other(
            dreamWorld = PokemonSprites(
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
            home = PokemonSprites(
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
            showdown = PokemonSprites(
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
        PokemonStat(
            baseStat = 1,
            effort = 2,
            stat = NamedAPIResource(name = "StatInfoName1", url = "StatInfoUrl1")
        ),
        PokemonStat(
            baseStat = 3,
            effort = 4,
            stat = NamedAPIResource(name = "StatInfoName2", url = "StatInfoUrl2")
        ),
        PokemonStat(
            baseStat = 5,
            effort = 6,
            stat = NamedAPIResource(name = "StatInfoName3", url = "StatInfoUrl3")
        )
    ),
    types = listOf(
        PokemonType(slot = 1, type = NamedAPIResource(name = "TypeInfoName1", url = "TypeInfoUrl1")),
        PokemonType(slot = 2, type = NamedAPIResource(name = "TypeInfoName2", url = "TypeInfoUrl2")),
        PokemonType(slot = 3, type = NamedAPIResource(name = "TypeInfoName3", url = "TypeInfoUrl3")),
        PokemonType(slot = 4, type = NamedAPIResource(name = "TypeInfoName4", url = "TypeInfoUrl4"))
    ),
    weight = 305
)