package com.bowoon.domain

import com.bowoon.data.repository.UserDataRepository
import com.bowoon.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoritePokemonUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(): Flow<List<Pokemon>> =
        userDataRepository.userData.map { it.favoriteList }
}