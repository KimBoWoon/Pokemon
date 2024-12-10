package com.bowoon.model

data class Evolution(
    val babyTriggerItem: NamedAPIResource? = null,
    val chain: ChainLink? = null,
    val id: Int? = null
)

data class ChainLink(
    val evolutionDetails: List<EvolutionDetail>? = null,
    val evolvesTo: List<ChainLink>? = null,
    val isBaby: Boolean? = null,
    val species: NamedAPIResource? = null
)

data class EvolutionDetail(
    val gender: Int? = null,
    val heldItem: NamedAPIResource? = null,
    val item: NamedAPIResource? = null,
    val knownMove: NamedAPIResource? = null,
    val knownMoveType: NamedAPIResource? = null,
    val location: NamedAPIResource? = null,
    val minAffection: Int? = null,
    val minBeauty: Int? = null,
    val minHappiness: Int? = null,
    val minLevel: Int? = null,
    val needsOverworldRain: Boolean? = null,
    val partySpecies: NamedAPIResource? = null,
    val partyType: NamedAPIResource? = null,
    val relativePhysicalStats: Int? = null,
    val timeOfDay: String? = null,
    val tradeSpecies: NamedAPIResource? = null,
    val trigger: NamedAPIResource? = null,
    val turnUpsideDown: Boolean? = null
)