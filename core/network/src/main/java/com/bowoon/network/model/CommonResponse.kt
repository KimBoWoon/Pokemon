package com.bowoon.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Name(
    @SerialName("language")
    val language: Language? = null,
    @SerialName("name")
    val name: String? = null
)

@Serializable
data class Language(
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
data class GenerationGameIndex(
    @SerialName("game_index")
    val gameIndex: Int? = null,
    @SerialName("generation")
    val generation: Generation? = null
)

@Serializable
data class Generation(
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
data class Pokemon(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Item(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class NamedAPIResource(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

fun NamedAPIResource.asExternalModel(): com.bowoon.model.NamedAPIResource =
    com.bowoon.model.NamedAPIResource(
        name = name,
        url = url
    )

fun List<NamedAPIResource>.asExternalModel(): List<com.bowoon.model.NamedAPIResource> =
    map {
        com.bowoon.model.NamedAPIResource(
            name = it.name,
            url = it.url
        )
    }