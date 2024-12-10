package com.bowoon.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemsResponse(
    @SerialName("attributes")
    val attributes: List<Attribute>? = null,
    @SerialName("baby_trigger_for")
    val babyTriggerFor: BabyTriggerFor? = null,
    @SerialName("category")
    val category: Category? = null,
    @SerialName("cost")
    val cost: Int? = null,
    @SerialName("effect_entries")
    val effectEntries: List<EffectEntry>? = null,
    @SerialName("flavor_text_entries")
    val flavorTextEntries: List<AbilityFlavorText>? = null,
    @SerialName("fling_effect")
    val flingEffect: FlingEffect? = null,
    @SerialName("fling_power")
    val flingPower: Int? = null,
    @SerialName("game_indices")
    val gameIndices: List<VersionGameIndex>? = null,
    @SerialName("held_by_pokemon")
    val heldByPokemon: List<HeldByPokemon>? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("names")
    val names: List<Name>? = null,
    @SerialName("sprites")
    val sprites: ItemSprites? = null
)

@Serializable
data class Attribute(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class BabyTriggerFor(
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Category(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class EffectEntry(
    @SerialName("effect")
    val effect: String? = null,
    @SerialName("language")
    val language: Language? = null,
    @SerialName("short_effect")
    val shortEffect: String? = null
)

@Serializable
data class AbilityFlavorText(
    @SerialName("language")
    val language: Language? = null,
    @SerialName("text")
    val text: String? = null,
    @SerialName("version_group")
    val versionGroup: VersionGroup? = null
)

@Serializable
data class FlingEffect(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class HeldByPokemon(
    @SerialName("pokemon")
    val pokemon: Pokemon? = null,
    @SerialName("version_details")
    val versionDetails: List<VersionDetail>? = null
)

@Serializable
data class VersionDetail(
    @SerialName("rarity")
    val rarity: Int? = null,
    @SerialName("version")
    val version: Version? = null
)

@Serializable
data class ItemSprites(
    @SerialName("default")
    val default: String? = null
)