package com.bowoon.model

data class Name(
    val language: Language? = null,
    val name: String? = null
)

data class Language(
    val name: String? = null,
    val url: String? = null
)

data class Species(
    val name: String? = null,
    val url: String? = null
)

data class VersionGroup(
    val name: String? = null,
    val url: String? = null
)

data class GenerationGameIndex(
    val gameIndex: Int? = null,
    val generation: Generation? = null
)

data class Generation(
    val name: String? = null,
    val url: String? = null
)

data class Version(
    val name: String? = null,
    val url: String? = null
)

//data class Pokemon(
//    val name: String? = null,
//    val url: String? = null
//)

data class Item(
    val name: String? = null,
    val url: String? = null
)

data class NamedAPIResource(
    val name: String? = null,
    val url: String? = null
)