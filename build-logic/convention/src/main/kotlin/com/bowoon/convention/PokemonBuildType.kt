package com.bowoon.convention

/**
 * This is shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class PokemonBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}
