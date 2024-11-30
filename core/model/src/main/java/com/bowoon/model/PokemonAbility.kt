package com.bowoon.model

data class PokemonAbility(
    val abilities: List<Ability>? = null,
    val baseExperience: Int? = null,
    val cries: Cries? = null,
    val forms: List<Form>? = null,
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
//    val pastTypes: List<Any>? = null,
    val species: Species? = null,
    val sprites: Sprites? = null,
    val stats: List<Stat>? = null,
    val types: List<Type>? = null,
    val weight: Int? = null
) {
    fun getImageUrl(): String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}

data class Ability(
    val ability: AbilityInfo? = null,
    val isHidden: Boolean? = null,
    val slot: Int? = null
)

data class AbilityInfo(
    val name: String? = null,
    val url: String? = null
)

data class Cries(
    val latest: String? = null,
    val legacy: String? = null
)

data class Form(
    val name: String? = null,
    val url: String? = null
)

data class GameIndice(
    val gameIndex: Int? = null,
    val version: Version? = null
)

data class PokemonHeldItem(
    val item: Item? = null,
    val versionDetails: List<PokemonHeldItemVersion>? = null
)

data class PokemonHeldItemVersion(
    val version: Version? = null,
    val rarity: Int? = null
)

data class Item(
    val name: String? = null,
    val url: String? = null
)

data class Version(
    val name: String? = null,
    val url: String? = null
)

data class Move(
    val move: MoveInfo? = null,
    val versionGroupDetails: List<VersionGroupDetail>? = null
)


data class MoveInfo(
    val name: String? = null,
    val url: String? = null
)

data class VersionGroupDetail(
    val levelLearnedAt: Int? = null,
    val moveLearnMethod: MoveLearnMethod? = null,
    val versionGroup: VersionGroup? = null
)

data class MoveLearnMethod(
    val name: String? = null,
    val url: String? = null
)

data class VersionGroup(
    val name: String? = null,
    val url: String? = null
)

data class Species(
    val name: String? = null,
    val url: String? = null
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
    val stat: StatInfo? = null
)

data class StatInfo(
    val name: String? = null,
    val url: String? = null
)

data class Type(
    val slot: Int? = null,
    val type: TypeInfo? = null
)

data class TypeInfo(
    val name: String? = null,
    val url: String? = null
)