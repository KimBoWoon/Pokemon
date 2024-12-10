package com.bowoon.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesResponse(
    @SerialName("base_happiness")
    val baseHappiness: Int? = null,
    @SerialName("capture_rate")
    val captureRate: Int? = null,
    @SerialName("color")
    val color: Color? = null,
    @SerialName("egg_groups")
    val eggGroups: List<EggGroup>? = null,
    @SerialName("evolution_chain")
    val evolutionChain: EvolutionChain? = null,
    @SerialName("evolves_from_species")
    val evolvesFromSpecies: EvolvesFromSpecies? = null,
    @SerialName("flavor_text_entries")
    val flavorTextEntries: List<VersionGroupFlavorText>? = null,
    @SerialName("form_descriptions")
    val formDescriptions: List<FormDescription>? = null,
    @SerialName("forms_switchable")
    val formsSwitchable: Boolean? = null,
    @SerialName("gender_rate")
    val genderRate: Int? = null,
    @SerialName("genera")
    val genera: List<Genera>? = null,
    @SerialName("generation")
    val generation: Generation? = null,
    @SerialName("growth_rate")
    val growthRate: GrowthRate? = null,
    @SerialName("habitat")
    val habitat: PokemonHabitatResponse? = null,
    @SerialName("has_gender_differences")
    val hasGenderDifferences: Boolean? = null,
    @SerialName("hatch_counter")
    val hatchCounter: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("is_baby")
    val isBaby: Boolean? = null,
    @SerialName("is_legendary")
    val isLegendary: Boolean? = null,
    @SerialName("is_mythical")
    val isMythical: Boolean? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("names")
    val names: List<Name>? = null,
    @SerialName("order")
    val order: Int? = null,
    @SerialName("pokedex_numbers")
    val pokedexNumbers: List<PokedexNumber>? = null,
    @SerialName("shape")
    val shape: Shape? = null,
    @SerialName("varieties")
    val varieties: List<Variety>? = null
)

@Serializable
data class Color(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class EggGroup(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class EvolutionChain(
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class EvolvesFromSpecies(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class VersionGroupFlavorText(
    @SerialName("flavor_text")
    val flavorText: String? = null,
    @SerialName("language")
    val language: Language? = null,
    @SerialName("version")
    val version: Version? = null
)

@Serializable
data class FormDescription(
    @SerialName("description")
    val description: String? = null,
    @SerialName("language")
    val language: Language? = null
)

@Serializable
data class Genera(
    @SerialName("genus")
    val genus: String? = null,
    @SerialName("language")
    val language: Language? = null
)

@Serializable
data class GrowthRate(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class PokedexNumber(
    @SerialName("entry_number")
    val entryNumber: Int? = null,
    @SerialName("pokedex")
    val pokedex: Pokedex? = null
)

@Serializable
data class Pokedex(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Shape(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Variety(
    @SerialName("is_default")
    val isDefault: Boolean? = null,
    @SerialName("pokemon")
    val pokemon: Pokemon? = null
)