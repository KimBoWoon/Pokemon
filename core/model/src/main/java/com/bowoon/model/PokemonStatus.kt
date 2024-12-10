package com.bowoon.model

data class PokemonStatus(
    val abilities: List<Ability>? = null,
    val baseExperience: Int? = null,
    val cries: Cries? = null,
    val forms: List<NamedAPIResource>? = null,
    val gameIndices: List<GameIndice>? = null,
    val height: Int? = null,
    val heldItems: List<PokemonHeldItem>? = null,
    val id: Int? = null,
    val isDefault: Boolean? = null,
    val locationAreaEncounters: String? = null,
    val moves: List<Move>? = null,
    val name: String? = null,
    val order: Int? = null,
//    val pastAbilities: List<Any>? = null,
    val pastTypes: List<PokemonTypePast>? = null,
    val species: NamedAPIResource? = null,
    val sprites: Sprites? = null,
    val stats: List<Stat>? = null,
    val types: List<Type>? = null,
    val weight: Int? = null
) {
    fun getImageUrl(): String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}

data class Ability(
    val ability: NamedAPIResource? = null,
    val isHidden: Boolean? = null,
    val slot: Int? = null
)

data class Cries(
    val latest: String? = null,
    val legacy: String? = null
)

data class GameIndice(
    val gameIndex: Int? = null,
    val version: NamedAPIResource? = null
)

data class PokemonHeldItem(
    val item: NamedAPIResource? = null,
    val versionDetails: List<PokemonHeldItemVersion>? = null
)

data class PokemonHeldItemVersion(
    val version: NamedAPIResource? = null,
    val rarity: Int? = null
)

data class Move(
    val move: NamedAPIResource? = null,
    val versionGroupDetails: List<VersionGroupDetail>? = null
)

data class VersionGroupDetail(
    val levelLearnedAt: Int? = null,
    val moveLearnMethod: NamedAPIResource? = null,
    val versionGroup: NamedAPIResource? = null
)

data class Sprites(
    val backDefault: String? = null,
    val backFemale: String? = null,
    val backShiny: String? = null,
    val backShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val frontShiny: String? = null,
    val frontShinyFemale: String? = null,
    val other: Other? = null,
//    val versions: Map<String, Map<String, Map<String, Map<String, String>>>>? = null
)

data class Other(
    val dreamWorld: Sprites? = null,
    val home: Sprites? = null,
    val officialArtwork: OfficialArtwork? = null,
    val showdown: Sprites? = null
)

data class OfficialArtwork(
    val frontDefault: String? = null,
    val frontShiny: String? = null
)

data class Stat(
    val baseStat: Int? = null,
    val effort: Int? = null,
    val stat: NamedAPIResource? = null
)

data class Type(
    val slot: Int? = null,
    val type: NamedAPIResource? = null
)

data class PokemonTypePast(
    val generation: NamedAPIResource? = null,
    val types: PokemonType? = null
)

data class PokemonType(
    val slot: Int? = null,
    val type: NamedAPIResource? = null
)