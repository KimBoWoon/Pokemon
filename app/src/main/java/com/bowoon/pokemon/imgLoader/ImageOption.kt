package com.bowoon.pokemon.imgLoader

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import coil3.request.CachePolicy

/**
 * 이미지를 로드하는대 필요한 정보들이 담긴 option
 *
 * - compose 사용시 Modifier에서 제공하는 RoundedCornerShape, CircleShape를 사용
 */
data class ImageOption(
    val placeholder: Drawable? = null,
    @DrawableRes
    val placeholderId: Int? = null,
    val error: Drawable? = null,
    @DrawableRes
    val errorId: Int? = null,
    val diskCachePolicy: CachePolicy = CachePolicy.ENABLED,
    val memoryCachePolicy: CachePolicy = CachePolicy.ENABLED,
    val crossFadeDuration: Int = 0,
    val width: Int? = null,
    val height: Int? = null,
    val headerMap: Map<String, String>? = null,
    val radius: Int = 0,
    val topLeft: Int = 0,
    val topRight: Int = 0,
    val bottomLeft: Int = 0,
    val bottomRight: Int = 0,
    val isCircleCropTransformation: Boolean = false,
    val allowHardware: Boolean = true
)