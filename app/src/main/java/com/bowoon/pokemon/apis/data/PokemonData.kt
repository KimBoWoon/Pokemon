package com.bowoon.pokemon.apis.data

import android.os.Parcelable
import androidx.annotation.Keep
import com.bowoon.pokemon.apis.data.Pokemon
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class PokemonData(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Pokemon>? = null
) : Parcelable