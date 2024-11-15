package com.bowoon.network.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Keep
data class PokemonListResponse(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Pokemon>? = null
) : Parcelable