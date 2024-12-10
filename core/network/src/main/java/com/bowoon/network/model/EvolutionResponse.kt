package com.bowoon.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EvolutionResponse(
    @SerialName("baby_trigger_item")
    val babyTriggerItem: NamedAPIResource? = null,
    @SerialName("chain")
    val chain: ChainLink? = null,
    @SerialName("id")
    val id: Int? = null
)

@Serializable
data class ChainLink(
    @SerialName("evolution_details")
    val evolutionDetails: List<EvolutionDetail>? = null,
    @SerialName("evolves_to")
    val evolvesTo: List<ChainLink>? = null,
    @SerialName("is_baby")
    val isBaby: Boolean? = null,
    @SerialName("species")
    val species: NamedAPIResource? = null
)

@Serializable
data class EvolutionDetail(
    @SerialName("gender")
    val gender: Int? = null,
    @SerialName("held_item")
    val heldItem: NamedAPIResource? = null,
    @SerialName("item")
    val item: NamedAPIResource? = null,
    @SerialName("known_move")
    val knownMove: NamedAPIResource? = null,
    @SerialName("known_move_type")
    val knownMoveType: NamedAPIResource? = null,
    @SerialName("location")
    val location: NamedAPIResource? = null,
    @SerialName("min_affection")
    val minAffection: Int? = null,
    @SerialName("min_beauty")
    val minBeauty: Int? = null,
    @SerialName("min_happiness")
    val minHappiness: Int? = null,
    @SerialName("min_level")
    val minLevel: Int? = null,
    @SerialName("needs_overworld_rain")
    val needsOverworldRain: Boolean? = null,
    @SerialName("party_species")
    val partySpecies: NamedAPIResource? = null,
    @SerialName("party_type")
    val partyType: NamedAPIResource? = null,
    @SerialName("relative_physical_stats")
    val relativePhysicalStats: Int? = null,
    @SerialName("time_of_day")
    val timeOfDay: String? = null,
    @SerialName("trade_species")
    val tradeSpecies: NamedAPIResource? = null,
    @SerialName("trigger")
    val trigger: NamedAPIResource? = null,
    @SerialName("turn_upside_down")
    val turnUpsideDown: Boolean? = null
)

fun EvolutionResponse.asExternalModel(): com.bowoon.model.Evolution =
    com.bowoon.model.Evolution(
        babyTriggerItem = babyTriggerItem?.asExternalModel(),
        chain = chain?.asExternalModel(),
        id = id
    )

fun ChainLink.asExternalModel(): com.bowoon.model.ChainLink =
    com.bowoon.model.ChainLink(
        evolutionDetails = evolutionDetails?.asExternalModel(),
        evolvesTo = evolvesTo?.asExternalModel(),
        isBaby = isBaby,
        species = species?.asExternalModel()
    )

@JvmName("ChainLinkExternalModel")
fun List<ChainLink>.asExternalModel(): List<com.bowoon.model.ChainLink> =
    map {
        com.bowoon.model.ChainLink(
            evolutionDetails = it.evolutionDetails?.asExternalModel(),
            evolvesTo = it.evolvesTo?.asExternalModel(),
            isBaby = it.isBaby,
            species = it.species?.asExternalModel()
        )
    }

fun List<EvolutionDetail>.asExternalModel(): List<com.bowoon.model.EvolutionDetail> =
    map {
        com.bowoon.model.EvolutionDetail(
            gender = it.gender,
            heldItem = it.heldItem?.asExternalModel(),
            item = it.item?.asExternalModel(),
            knownMove = it.knownMove?.asExternalModel(),
            knownMoveType = it.knownMoveType?.asExternalModel(),
            location = it.location?.asExternalModel(),
            minAffection = it.minAffection,
            minBeauty = it.minBeauty,
            minHappiness = it.minHappiness,
            minLevel = it.minLevel,
            needsOverworldRain = it.needsOverworldRain,
            partySpecies = it.partySpecies?.asExternalModel(),
            partyType = it.partyType?.asExternalModel(),
            relativePhysicalStats = it.relativePhysicalStats,
            timeOfDay = it.timeOfDay,
            tradeSpecies = it.tradeSpecies?.asExternalModel(),
            trigger = it.trigger?.asExternalModel(),
            turnUpsideDown = it.turnUpsideDown
        )
    }