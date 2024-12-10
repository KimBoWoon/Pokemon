package com.bowoon.model


data class PokemonSpecies(
    val baseHappiness: Int? = null,
    val captureRate: Int? = null,
    val color: Color? = null,
    val eggGroups: List<EggGroup>? = null,
    val evolutionChain: EvolutionChain? = null,
    val evolvesFromSpecies: EvolvesFromSpecies? = null,
    val flavorTextEntries: List<VersionGroupFlavorText>? = null,
    val formDescriptions: List<FormDescription>? = null,
    val formsSwitchable: Boolean? = null,
    val genderRate: Int? = null,
    val genera: List<Genera>? = null,
    val generation: Generation? = null,
    val growthRate: GrowthRate? = null,
    val habitat: PokemonHabitat? = null,
    val hasGenderDifferences: Boolean? = null,
    val hatchCounter: Int? = null,
    val id: Int? = null,
    val isBaby: Boolean? = null,
    val isLegendary: Boolean? = null,
    val isMythical: Boolean? = null,
    val name: String? = null,
    val names: List<Name>? = null,
    val order: Int? = null,
    val pokedexNumbers: List<PokedexNumber>? = null,
    val shape: Shape? = null,
    val varieties: List<Variety>? = null
)

data class Color(
    val name: String? = null,
    val url: String? = null
)

data class EggGroup(
    val name: String? = null,
    val url: String? = null
)

data class EvolutionChain(
    val url: String? = null
)

data class EvolvesFromSpecies(
    val name: String? = null,
    val url: String? = null
)

data class VersionGroupFlavorText(
    val flavorText: String? = null,
    val language: Language? = null,
    val version: Version? = null
)

data class FormDescription(
    val description: String? = null,
    val language: Language? = null
)

data class Genera(
    val genus: String? = null,
    val language: Language? = null
)

data class GrowthRate(
    val name: String? = null,
    val url: String? = null
)

data class PokedexNumber(
    val entryNumber: Int? = null,
    val pokedex: Pokedex? = null
)

data class Pokedex(
    val name: String? = null,
    val url: String? = null
)

data class Shape(
    val name: String? = null,
    val url: String? = null
)

data class Variety(
    val isDefault: Boolean? = null,
    val pokemon: Pokemon? = null
)