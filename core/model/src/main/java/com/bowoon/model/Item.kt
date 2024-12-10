package com.bowoon.model


data class Items(
    val attributes: List<Attribute>? = null,
    val babyTriggerFor: BabyTriggerFor? = null,
    val category: Category? = null,
    val cost: Int? = null,
    val effectEntries: List<EffectEntry>? = null,
    val flavorTextEntries: List<AbilityFlavorText>? = null,
    val flingEffect: FlingEffect? = null,
    val flingPower: Int? = null,
    val gameIndices: List<GameIndice>? = null,
    val heldByPokemon: List<HeldByPokemon>? = null,
    val id: Int? = null,
    val name: String? = null,
    val names: List<Name>? = null,
    val sprites: ItemSprites? = null
)

data class Attribute(
    val name: String? = null,
    val url: String? = null
)

data class BabyTriggerFor(
    val url: String? = null
)

data class Category(
    val name: String? = null,
    val url: String? = null
)

data class EffectEntry(
    val effect: String? = null,
    val language: Language? = null,
    val shortEffect: String? = null
)

data class AbilityFlavorText(
    val language: Language? = null,
    val text: String? = null,
    val versionGroup: VersionGroup? = null
)

data class FlingEffect(
    val name: String? = null,
    val url: String? = null
)

data class HeldByPokemon(
    val pokemon: Pokemon? = null,
    val versionDetails: List<VersionDetail>? = null
)

data class VersionDetail(
    val rarity: Int? = null,
    val version: Version? = null
)

data class ItemSprites(
    val default: String? = null
)