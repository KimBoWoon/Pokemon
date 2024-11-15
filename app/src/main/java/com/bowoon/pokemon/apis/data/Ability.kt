package com.bowoon.pokemon.apis.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null
)