package com.bowoon.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun AbilityResponse.asExternalModel(): com.bowoon.model.PokemonAbility =
    com.bowoon.model.PokemonAbility(
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
fun List<Type>.asExternalModel(): List<com.bowoon.model.Type> =
    map {
        com.bowoon.model.Type(
            slot = it.slot,
            type = it.type?.asExternalModel()
        )
    }

fun TypeInfo.asExternalModel(): com.bowoon.model.TypeInfo =
    com.bowoon.model.TypeInfo(
        name = name,
        url = url
    )

fun Species.asExternalModel(): com.bowoon.model.Species =
    com.bowoon.model.Species(
        name = name,
        url = url
    )

fun Sprites.asExternalModel(): com.bowoon.model.Sprites =
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

@JvmName("MoveInfoExternalModel")
fun List<MoveInfo>.asExternalModel(): List<com.bowoon.model.MoveInfo> =
    map {
        com.bowoon.model.MoveInfo(
            name = it.name,
            url = it.url
        )
    }

@JvmName("FormExternalModel")
fun List<Form>.asExternalModel(): List<com.bowoon.model.Form> =
    map {
        com.bowoon.model.Form(
            name = it.name,
            url = it.url
        )
    }

fun Cries.asExternalModel(): com.bowoon.model.Cries =
    com.bowoon.model.Cries(
        latest = latest,
        legacy = legacy
    )

@JvmName("MoveExternalModel")
fun List<Move>.asExternalModel(): List<com.bowoon.model.Move> =
    map {
        com.bowoon.model.Move(
            move = it.move?.asExternalModel(),
            versionGroupDetails = it.versionGroupDetails?.asExternalModel()
        )
    }

fun MoveInfo.asExternalModel(): com.bowoon.model.MoveInfo =
    com.bowoon.model.MoveInfo(
        name = name,
        url = url
    )

@JvmName("VersionGroupDetailExternalModel")
fun List<VersionGroupDetail>.asExternalModel(): List<com.bowoon.model.VersionGroupDetail> =
    map {
        com.bowoon.model.VersionGroupDetail(
            levelLearnedAt = it.levelLearnedAt,
            moveLearnMethod = it.moveLearnMethod?.asExternalModel(),
            versionGroup = it.versionGroup?.asExternalModel()
        )
    }

fun MoveLearnMethod.asExternalModel(): com.bowoon.model.MoveLearnMethod =
    com.bowoon.model.MoveLearnMethod(
        name = name,
        url = url
    )

fun VersionGroup.asExternalModel(): com.bowoon.model.VersionGroup =
    com.bowoon.model.VersionGroup(
        name = name,
        url = url
    )

@JvmName("AbilitiesExternalModel")
fun List<Ability>.asExternalModel(): List<com.bowoon.model.Ability> =
    map { abilities ->
        com.bowoon.model.Ability(
            ability = abilities.ability?.asExternalModel(),
            isHidden = abilities.isHidden,
            slot = abilities.slot
        )
    }

fun AbilityInfo.asExternalModel(): com.bowoon.model.AbilityInfo =
    com.bowoon.model.AbilityInfo(
        name = name,
        url = url
    )

fun List<PokemonHeldItem>.asExternalModel(): List<com.bowoon.model.PokemonHeldItem> =
    map {
        com.bowoon.model.PokemonHeldItem(
            item = it.item?.asExternalModel(),
            versionDetails = it.versionDetails?.asExternalModel()
        )
    }

fun Item.asExternalModel(): com.bowoon.model.Item =
    com.bowoon.model.Item(
        name = name,
        url = url
    )

@JvmName("PokemonHeldItemVersionExternalModel")
fun List<PokemonHeldItemVersion>.asExternalModel(): List<com.bowoon.model.PokemonHeldItemVersion> =
    map {
        com.bowoon.model.PokemonHeldItemVersion(
            version = it.version?.asExternalModel(),
            rarity = it.rarity
        )
    }

fun Version.asExternalModel(): com.bowoon.model.Version =
    com.bowoon.model.Version(
        name = name,
        url = url
    )

@JvmName("GameIndiceExternalModel")
fun List<GameIndice>.asExternalModel(): List<com.bowoon.model.GameIndice> =
    map {
        com.bowoon.model.GameIndice(
            gameIndex = it.gameIndex,
            version = it.version?.asExternalModel()
        )
    }

@JvmName("StatsExternalModel")
fun List<Stat>.asExternalModel(): List<com.bowoon.model.Stat> =
    map { stats ->
        com.bowoon.model.Stat(
            baseStat = stats.baseStat,
            effort = stats.effort,
            stat = stats.stat?.asExternalModel()
        )
    }

fun StatInfo.asExternalModel(): com.bowoon.model.StatInfo =
    com.bowoon.model.StatInfo(
        name = name,
        url = url
    )

@Serializable
data class AbilityResponse(
    @SerialName("abilities")
    val abilities: List<Ability>? = null,
    @SerialName("base_experience")
    val baseExperience: Int? = null,
    @SerialName("cries")
    val cries: Cries? = null,
    @SerialName("forms")
    val forms: List<Form>? = null,
    @SerialName("game_indices")
    val gameIndices: List<GameIndice>? = null,
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
    val moves: List<Move>? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("order")
    val order: Int? = null,
//    @SerialName("past_abilities")
//    val pastAbilities: List<Any>? = null,
//    @SerialName("past_types")
//    val pastTypes: List<Any>? = null,
    @SerialName("species")
    val species: Species? = null,
    @SerialName("sprites")
    val sprites: Sprites? = null,
    @SerialName("stats")
    val stats: List<Stat>? = null,
    @SerialName("types")
    val types: List<Type>? = null,
    @SerialName("weight")
    val weight: Int? = null
)

@Serializable
data class Ability(
    @SerialName("ability")
    val ability: AbilityInfo? = null,
    @SerialName("is_hidden")
    val isHidden: Boolean? = null,
    @SerialName("slot")
    val slot: Int? = null
)

@Serializable
data class AbilityInfo(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Cries(
    @SerialName("latest")
    val latest: String? = null,
    @SerialName("legacy")
    val legacy: String? = null
)

@Serializable
data class Form(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class GameIndice(
    @SerialName("game_index")
    val gameIndex: Int? = null,
    @SerialName("version")
    val version: Version? = null
)

@Serializable
data class PokemonHeldItem(
    @SerialName("item")
    val item: Item? = null,
    @SerialName("version_details")
    val versionDetails: List<PokemonHeldItemVersion>? = null
)

@Serializable
data class PokemonHeldItemVersion(
    @SerialName("version")
    val version: Version? = null,
    @SerialName("rarity")
    val rarity: Int? = null
)

@Serializable
data class Item(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Version(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Move(
    @SerialName("move")
    val move: MoveInfo? = null,
    @SerialName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>? = null
)

@Serializable
data class MoveInfo(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class VersionGroupDetail(
    @SerialName("level_learned_at")
    val levelLearnedAt: Int? = null,
    @SerialName("move_learn_method")
    val moveLearnMethod: MoveLearnMethod? = null,
    @SerialName("version_group")
    val versionGroup: VersionGroup? = null
)

@Serializable
data class MoveLearnMethod(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class VersionGroup(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Species(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Sprites(
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
    val dreamWorld: Sprites? = null,
    @SerialName("home")
    val home: Sprites? = null,
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtwork? = null,
    @SerialName("showdown")
    val showdown: Sprites? = null
)

@Serializable
data class OfficialArtwork(
    @SerialName("front_default")
    val frontDefault: String? = null,
    @SerialName("front_shiny")
    val frontShiny: String? = null
)

@Serializable
data class Stat(
    @SerialName("base_stat")
    val baseStat: Int? = null,
    @SerialName("effort")
    val effort: Int? = null,
    @SerialName("stat")
    val stat: StatInfo? = null
)

@Serializable
data class StatInfo(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Type(
    @SerialName("slot")
    val slot: Int? = null,
    @SerialName("type")
    val type: TypeInfo? = null
)

@Serializable
data class TypeInfo(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)