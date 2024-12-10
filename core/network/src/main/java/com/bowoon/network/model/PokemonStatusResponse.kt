package com.bowoon.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun PokemonStatusResponse.asExternalModel(): com.bowoon.model.PokemonStatus =
    com.bowoon.model.PokemonStatus(
        name = name,
        abilities = abilities?.asExternalModel(),
        id = id,
        stats = stats?.asExternalModel(),
        heldItems = heldItems?.asExternalModel(),
        height = height,
        gameIndices = gameIndices?.asExternalModel(),
        forms = forms?.asExternalModel(),
        cries = cries?.asExternalModel(),
        baseExperience = baseExperience,
        isDefault = isDefault,
        locationAreaEncounters = locationAreaEncounters,
        moves = moves?.asExternalModel(),
        order = order,
        species = species?.asExternalModel(),
        sprites = sprites?.asExternalModel(),
        types = types?.asExternalModel(),
        weight = weight
    )

@JvmName("TypeExternalModel")
fun List<PokemonType>.asExternalModel(): List<com.bowoon.model.Type> =
    map {
        com.bowoon.model.Type(
            slot = it.slot,
            type = it.type?.asExternalModel()
        )
    }

fun PokemonSprites.asExternalModel(): com.bowoon.model.Sprites =
    com.bowoon.model.Sprites(
        backDefault = backDefault,
        backFemale = backFemale,
        backShiny = backShiny,
        backShinyFemale = backShinyFemale,
        frontDefault = frontDefault,
        frontFemale = frontFemale,
        frontShiny = frontShiny,
        frontShinyFemale = frontShinyFemale,
        other = other?.asExternalModel()
    )

fun Other.asExternalModel(): com.bowoon.model.Other =
    com.bowoon.model.Other(
        dreamWorld = dreamWorld?.asExternalModel(),
        home = home?.asExternalModel(),
        officialArtwork = officialArtwork?.asExternalModel(),
        showdown = showdown?.asExternalModel()
    )

fun OfficialArtwork.asExternalModel(): com.bowoon.model.OfficialArtwork =
    com.bowoon.model.OfficialArtwork(
        frontDefault = frontDefault,
        frontShiny = frontShiny
    )

fun PokemonCries.asExternalModel(): com.bowoon.model.Cries =
    com.bowoon.model.Cries(
        latest = latest,
        legacy = legacy
    )

@JvmName("MoveExternalModel")
fun List<PokemonMove>.asExternalModel(): List<com.bowoon.model.Move> =
    map {
        com.bowoon.model.Move(
            move = it.move?.asExternalModel(),
            versionGroupDetails = it.versionGroupDetails?.asExternalModel()
        )
    }

@JvmName("VersionGroupDetailExternalModel")
fun List<PokemonMoveVersion>.asExternalModel(): List<com.bowoon.model.VersionGroupDetail> =
    map {
        com.bowoon.model.VersionGroupDetail(
            levelLearnedAt = it.levelLearnedAt,
            moveLearnMethod = it.moveLearnMethod?.asExternalModel(),
            versionGroup = it.versionGroup?.asExternalModel()
        )
    }

@JvmName("AbilitiesExternalModel")
fun List<PokemonAbility>.asExternalModel(): List<com.bowoon.model.Ability> =
    map { abilities ->
        com.bowoon.model.Ability(
            ability = abilities.ability?.asExternalModel(),
            isHidden = abilities.isHidden,
            slot = abilities.slot
        )
    }

fun List<PokemonHeldItem>.asExternalModel(): List<com.bowoon.model.PokemonHeldItem> =
    map {
        com.bowoon.model.PokemonHeldItem(
            item = it.item?.asExternalModel(),
            versionDetails = it.versionDetails?.asExternalModel()
        )
    }

@JvmName("PokemonHeldItemVersionExternalModel")
fun List<PokemonHeldItemVersion>.asExternalModel(): List<com.bowoon.model.PokemonHeldItemVersion> =
    map {
        com.bowoon.model.PokemonHeldItemVersion(
            version = it.version?.asExternalModel(),
            rarity = it.rarity
        )
    }

@JvmName("GameIndiceExternalModel")
fun List<VersionGameIndex>.asExternalModel(): List<com.bowoon.model.GameIndice> =
    map {
        com.bowoon.model.GameIndice(
            gameIndex = it.gameIndex,
            version = it.version?.asExternalModel()
        )
    }

@JvmName("StatsExternalModel")
fun List<PokemonStat>.asExternalModel(): List<com.bowoon.model.Stat> =
    map { stats ->
        com.bowoon.model.Stat(
            baseStat = stats.baseStat,
            effort = stats.effort,
            stat = stats.stat?.asExternalModel()
        )
    }

@Serializable
data class PokemonStatusResponse(
    @SerialName("abilities")
    val abilities: List<PokemonAbility>? = null,
    @SerialName("base_experience")
    val baseExperience: Int? = null,
    @SerialName("cries")
    val cries: PokemonCries? = null,
    @SerialName("forms")
    val forms: List<NamedAPIResource>? = null,
    @SerialName("game_indices")
    val gameIndices: List<VersionGameIndex>? = null,
    @SerialName("height")
    val height: Int? = null,
    @SerialName("held_items")
    val heldItems: List<PokemonHeldItem>? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("is_default")
    val isDefault: Boolean? = null,
    @SerialName("location_area_encounters")
    val locationAreaEncounters: String? = null,
    @SerialName("moves")
    val moves: List<PokemonMove>? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("order")
    val order: Int? = null,
//    @SerialName("past_abilities")
//    val pastAbilities: List<Any>? = null,
    @SerialName("past_types")
    val pastTypes: List<PokemonTypePast>? = null,
    @SerialName("species")
    val species: NamedAPIResource? = null,
    @SerialName("sprites")
    val sprites: PokemonSprites? = null,
    @SerialName("stats")
    val stats: List<PokemonStat>? = null,
    @SerialName("types")
    val types: List<PokemonType>? = null,
    @SerialName("weight")
    val weight: Int? = null
)

@Serializable
data class PokemonAbility(
    @SerialName("ability")
    val ability: NamedAPIResource? = null,
    @SerialName("is_hidden")
    val isHidden: Boolean? = null,
    @SerialName("slot")
    val slot: Int? = null
)

@Serializable
data class PokemonCries(
    @SerialName("latest")
    val latest: String? = null,
    @SerialName("legacy")
    val legacy: String? = null
)

@Serializable
data class VersionGameIndex(
    @SerialName("game_index")
    val gameIndex: Int? = null,
    @SerialName("version")
    val version: NamedAPIResource? = null
)

@Serializable
data class PokemonHeldItem(
    @SerialName("item")
    val item: NamedAPIResource? = null,
    @SerialName("version_details")
    val versionDetails: List<PokemonHeldItemVersion>? = null
)

@Serializable
data class PokemonHeldItemVersion(
    @SerialName("version")
    val version: NamedAPIResource? = null,
    @SerialName("rarity")
    val rarity: Int? = null
)

@Serializable
data class PokemonMove(
    @SerialName("move")
    val move: NamedAPIResource? = null,
    @SerialName("version_group_details")
    val versionGroupDetails: List<PokemonMoveVersion>? = null
)

@Serializable
data class PokemonMoveVersion(
    @SerialName("level_learned_at")
    val levelLearnedAt: Int? = null,
    @SerialName("move_learn_method")
    val moveLearnMethod: NamedAPIResource? = null,
    @SerialName("version_group")
    val versionGroup: NamedAPIResource? = null
)

@Serializable
data class PokemonSprites(
    @SerialName("back_default")
    val backDefault: String? = null,
    @SerialName("back_female")
    val backFemale: String? = null,
    @SerialName("back_shiny")
    val backShiny: String? = null,
    @SerialName("back_shiny_female")
    val backShinyFemale: String? = null,
    @SerialName("front_default")
    val frontDefault: String? = null,
    @SerialName("front_female")
    val frontFemale: String? = null,
    @SerialName("front_shiny")
    val frontShiny: String? = null,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String? = null,
    @SerialName("other")
    val other: Other? = null,
//    @SerialName("versions")
//    val versions: Map<String, Map<String, Map<String, Map<String, String>>>>? = null
)

@Serializable
data class Other(
    @SerialName("dream_world")
    val dreamWorld: PokemonSprites? = null,
    @SerialName("home")
    val home: PokemonSprites? = null,
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtwork? = null,
    @SerialName("showdown")
    val showdown: PokemonSprites? = null
)

@Serializable
data class OfficialArtwork(
    @SerialName("front_default")
    val frontDefault: String? = null,
    @SerialName("front_shiny")
    val frontShiny: String? = null
)

@Serializable
data class PokemonStat(
    @SerialName("base_stat")
    val baseStat: Int? = null,
    @SerialName("effort")
    val effort: Int? = null,
    @SerialName("stat")
    val stat: NamedAPIResource? = null
)

@Serializable
data class PokemonType(
    @SerialName("slot")
    val slot: Int? = null,
    @SerialName("type")
    val type: NamedAPIResource? = null
)

@Serializable
data class PokemonTypePast(
    @SerialName("generation")
    val generation: NamedAPIResource? = null,
    @SerialName("types")
    val types: PokemonType? = null
)