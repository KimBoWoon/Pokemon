package com.bowoon.model


data class Location(
    val areas: List<Area>? = null,
    val gameIndices: List<GenerationGameIndex>? = null,
    val id: Int? = null,
    val name: String? = null,
    val names: List<Name>? = null,
    val region: Region? = null
)

data class Area(
    val name: String? = null,
    val url: String? = null
)

data class Region(
    val name: String? = null,
    val url: String? = null
)