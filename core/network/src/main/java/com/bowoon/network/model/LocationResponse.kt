package com.bowoon.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("areas")
    val areas: List<Area>? = null,
    @SerialName("game_indices")
    val gameIndices: List<GenerationGameIndex>? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("names")
    val names: List<Name>? = null,
    @SerialName("region")
    val region: Region? = null
)

@Serializable
data class Area(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Region(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)