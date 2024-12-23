package com.bowoon.pokemon

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bowoon.common.Log
import com.bowoon.model.Move
import com.bowoon.model.PokemonStatus
import com.bowoon.model.Stat
import com.bowoon.ui.ConfirmDialog
import com.bowoon.ui.TitleView
import com.bowoon.ui.dp100
import com.bowoon.ui.dp16
import com.bowoon.ui.dp20
import com.bowoon.ui.dp30
import com.bowoon.ui.dp300
import com.bowoon.ui.dp5
import com.bowoon.ui.dp50
import com.bowoon.ui.image.DynamicAsyncImageLoader
import com.bowoon.ui.sp10
import com.bowoon.ui.sp20
import com.bowoon.ui.theme.bug
import com.bowoon.ui.theme.dark
import com.bowoon.ui.theme.dragon
import com.bowoon.ui.theme.electric
import com.bowoon.ui.theme.fairy
import com.bowoon.ui.theme.fighting
import com.bowoon.ui.theme.fire
import com.bowoon.ui.theme.flying
import com.bowoon.ui.theme.ghost
import com.bowoon.ui.theme.grass
import com.bowoon.ui.theme.ground
import com.bowoon.ui.theme.ice
import com.bowoon.ui.theme.normal
import com.bowoon.ui.theme.poison
import com.bowoon.ui.theme.psychic
import com.bowoon.ui.theme.rock
import com.bowoon.ui.theme.steel
import com.bowoon.ui.theme.water
import kotlin.math.absoluteValue

@Composable
fun PokemonScreen(
    viewModel: PokemonVM = hiltViewModel()
) {
    val state: PokemonUiState by viewModel.pokemonUiState.collectAsStateWithLifecycle()

    PokemonScreen(
        state = state
    )
}

@Composable
fun PokemonScreen(
    state: PokemonUiState
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (state) {
            is PokemonUiState.Loading -> PokemonAbilityLoading()
            is PokemonUiState.Success -> PokemonAbilitySuccess(state.data)
            is PokemonUiState.Error -> PokemonAbilityError(state.throwable)
        }
    }
}

@Composable
fun BoxScope.PokemonAbilityLoading() {
    Log.d("PokemonInfo", "Loading...")
    CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center)
    )
}

@Composable
fun BoxScope.PokemonAbilitySuccess(
    pokemonStatus: PokemonStatus
) {
    val scrollState = rememberScrollState()

    Log.d("PokemonInfo", pokemonStatus.toString())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState)
    ) {
        TitleView(
            modifier = Modifier
                .fillMaxWidth()
                .height(dp50),
            title = "Pokemon Ability"
        )

        PokemonImageScreen(pokemonStatus)
        PokemonStatusScreen(pokemonStatus)
//        PokemonMoveHorizontalPager(pokemonStatus)
    }
}

@Composable
fun PokemonImageScreen(
    pokemonStatus: PokemonStatus
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = getTypeColor(pokemonStatus.types?.firstOrNull()?.type?.name),
                shape = RoundedCornerShape(bottomStart = dp30, bottomEnd = dp30)
            )
    ) {
        DynamicAsyncImageLoader(
            source = pokemonStatus.getImageUrl(),
            contentDescription = "PokemonImageLoad",
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = pokemonStatus.name ?: "",
            color = Color.White,
            fontSize = sp20,
            style = TextStyle(
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            )
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = dp5),
            text = pokemonStatus.types?.firstOrNull()?.type?.name ?: "",
            color = Color.White,
            fontSize = sp10,
            style = TextStyle(
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            )
        )
    }
}

@Composable
fun ColumnScope.PokemonStatusScreen(
    pokemonStatus: PokemonStatus
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = dp20, vertical = dp30)
            .align(Alignment.CenterHorizontally)
    ) {
        pokemonStatus.stats?.forEach {
            PokemonAbilityStatus(it)
        }
    }
}

@Composable
fun ColumnScope.PokemonMoveHorizontalPager(
    pokemonStatus: PokemonStatus
) {
    val pagerState = rememberPagerState(
        pageCount = { pokemonStatus.moves?.size ?: 0 }
    )
    val contentPadding = dp30
    val pageSpacing = dp16
    val scaleSizeRatio = 0.8f

    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally),
        state = pagerState,
        key = { index -> index },
        contentPadding = PaddingValues(horizontal = contentPadding),
        pageSpacing = pageSpacing,
    ) { index ->
        pokemonStatus.moves?.get(index)?.let { move ->
            Move(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = pagerState.currentPage - index + pagerState.currentPageOffsetFraction
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f),
                        )
                        lerp(
                            start = 1f,
                            stop = scaleSizeRatio,
                            fraction = pageOffset.absoluteValue.coerceIn(0f, 1f),
                        ).let {
                            scaleX = it
                            scaleY = it
                            val sign = if (pageOffset > 0) 1 else -1
                            translationX = sign * size.width * (1 - it) / 2
                        }
                    },
                move = move,
            )
        }
    }
}

@Composable
fun Move(
    modifier: Modifier,
    move: Move
) {
    Box(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(dp300)
                .background(color = Color.Yellow),
            text = move.move?.name ?: ""
        )
    }
}

@Composable
fun BoxScope.PokemonAbilityError(
    throwable: Throwable
) {
    val viewModel = hiltViewModel<PokemonVM>()

    Log.e("PokemonInfo", throwable.message.toString())

    ConfirmDialog(
        title = "로드 실패",
        message = throwable.message.toString(),
        confirmPair = "재시도" to { viewModel.pokemonUiState.restart() },
        dismissPair = "취소" to {}
    )
}

@Composable
fun ColumnScope.PokemonAbilityStatus(
    status: Stat
) {
    var progress by remember { mutableFloatStateOf(0f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "PokemonStatus"
    )

    LaunchedEffect(key1 = "updateProgress") {
        progress += ((status.baseStat ?: 0) % 101) / 100f
    }

    Row() {
        Text(
            modifier = Modifier.width(dp100),
            text = status.stat?.name ?: "",
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
        LinearProgressIndicator(
            modifier = Modifier.weight(1f)
                .align(Alignment.CenterVertically),
            color = Color.Blue,
            progress = { animatedProgress }
        )
    }
}

fun getTypeColor(
    type: String?
): Color = when (type) {
    "bug" -> bug
    "dark" -> dark
    "dragon" -> dragon
    "electric" -> electric
    "fire" -> fire
    "fairy" -> fairy
    "fighting" -> fighting
    "flying" -> flying
    "ghost" -> ghost
    "grass" -> grass
    "ground" -> ground
    "ice" -> ice
    "normal" -> normal
    "poison" -> poison
    "psychic" -> psychic
    "rock" -> rock
    "steel" -> steel
    "water" -> water
    else -> Color.Transparent
}