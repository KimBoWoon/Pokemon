package com.bowoon.network.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.bowoon.model.Pokemon
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Keep
data class PokemonResponse(
    val name: String? = null,
    val url: String? = null
) : Parcelable

fun PokemonResponse.asExternalModel(): Pokemon =
    Pokemon(
        name = name,
        url = url
    )