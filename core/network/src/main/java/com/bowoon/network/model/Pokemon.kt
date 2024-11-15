package com.bowoon.network.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Keep
data class Pokemon(
    val name: String? = null,
    val url: String? = null
) : Parcelable {
    fun getImageUrl(): String {
        val index = url?.split("/".toRegex())?.dropLast(1)?.last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}