package com.bowoon.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    @SerialName("abilities")
    val abilities: List<Abilities>? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("stats")
    val stats: List<Stats>? = null
)

@Serializable
data class Abilities(
    @SerialName("ability")
    val ability: Ability? = null,
    @SerialName("is_hidden")
    val isHidden: Boolean? = false,
    @SerialName("slot")
    val slot: Int? = 0
)

@Serializable
data class Ability(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Stats(
    @SerialName("base_stat")
    val baseStat: Int? = 0,
    @SerialName("effort")
    val effort: Int? = 0,
    @SerialName("stat")
    val stat: Stat? = Stat()
)

@Serializable
data class Stat(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)