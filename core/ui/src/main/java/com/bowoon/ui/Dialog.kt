package com.bowoon.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import com.bowoon.ui.theme.PokemonTheme

@Composable
fun ConfirmDialog(
    title: String = "",
    message: String = "",
    confirmPair: Pair<String, () -> Unit>,
    dismissPair: Pair<String?, (() -> Unit)?> = null to null
) {
    var shouldShowDialog by remember { mutableStateOf(true) }

    if (shouldShowDialog) {
        PokemonDialog(
            onDismiss = { shouldShowDialog = false },
            title,
            message,
            confirmPair,
            dismissPair
        )
    }
}

@Composable
private fun PokemonDialog(
    onDismiss: () -> Unit,
    title: String = "",
    message: String = "",
    confirmPair: Pair<String, () -> Unit>,
    dismissPair: Pair<String?, (() -> Unit)?> = null to null
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 20.dp)
                .background(color = Color.Transparent)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .shadow(elevation = dp1, shape = RoundedCornerShape(dp20))
                    .background(color = Color.White, shape = RoundedCornerShape(dp20))
                    .zIndex(1f)
            ) {
                if (title.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dp20),
                        text = title,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(dp20))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dp20),
                    text = message,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dp90)
                    .zIndex(0f)
                    .offset(y = -dp30),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (!dismissPair.first.isNullOrEmpty()) {
                    DialogButton(
                        modifier = Modifier
                            .background(color = Color(0xFFC2C2C2), shape = RoundedCornerShape(bottomStart = dp20))
                            .border(width = dp1, color = Color.Black)
                            .padding(top = dp30),
                        text = dismissPair.first ?: "",
                    ) {
                        dismissPair.second?.let { it() }
                        onDismiss()
                    }
                }
                DialogButton(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(bottomStart = if (!dismissPair.first.isNullOrEmpty()) dp0 else dp20, bottomEnd = dp20))
                        .border(width = dp1, color = Color.Black)
                        .padding(top = dp30),
                    text = confirmPair.first
                ) {
                    confirmPair.second()
                    onDismiss()
                }
            }
        }
    }
}

@Composable
fun RowScope.DialogButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(1f)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.Black
        )
    }
}

@Preview(showSystemUi = false)
@Composable
fun PokemonOneButtonDialogPreview() {
    PokemonTheme {
        PokemonDialog(
            onDismiss = {},
            title = "Title",
            message = "Message",
            confirmPair = "확인" to {}
        )
    }
}

@Preview(showSystemUi = false)
@Composable
fun PokemonTwoButtonDialogPreview() {
    PokemonTheme {
        PokemonDialog(
            onDismiss = {},
            title = "Title",
            message = "Message",
            confirmPair = "확인" to {},
            dismissPair = "취소" to {}
        )
    }
}