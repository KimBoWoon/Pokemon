package com.bowoon.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.bowoon.model.Pokemon
import com.bowoon.ui.image.DynamicAsyncImageLoader

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    isFavorite: Boolean,
    onPokemonClick: (String) -> Unit,
    updateFavoritePokemon: (Pokemon) -> Unit,
) {
    Box(
        modifier = Modifier.wrapContentSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    pokemon.url?.let { url ->
                        onPokemonClick(url)
                    }
                }
        ) {
            DynamicAsyncImageLoader(
                source = pokemon.getImageUrl(),
                contentDescription = "PokemonListImageLoad"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                maxLines = 1,
                text = pokemon.name ?: ""
            )
        }
        FavoriteButton(
            modifier = Modifier
                .padding(top = dp5, end = dp5)
                .wrapContentSize()
                .align(Alignment.TopEnd),
            isFavorite = isFavorite,
            onClick = { updateFavoritePokemon(pokemon) }
        )
    }
}