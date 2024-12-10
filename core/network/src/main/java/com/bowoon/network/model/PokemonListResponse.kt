package com.bowoon.network.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.bowoon.model.PokemonList
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Keep
data class PokemonListResponse(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonResponse>? = null
) : Parcelable

fun PokemonListResponse.asExternalModel(): PokemonList =
    PokemonList(
        count = count,
        next = next,
        previous = previous,
        results = results?.map { it.asExternalModel() }
    )

fun List<PokemonResponse>.asExternalModel(): List<com.bowoon.model.Pokemon> =
    map { it.asExternalModel() }