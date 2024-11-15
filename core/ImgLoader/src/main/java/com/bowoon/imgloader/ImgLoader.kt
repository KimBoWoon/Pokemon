package com.bowoon.imgloader

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.SingletonImageLoader
import coil3.asDrawable
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import coil3.compose.SubcomposeAsyncImageScope
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import coil3.request.target
import coil3.request.transformations
import coil3.size.Precision
import coil3.transform.CircleCropTransformation
import coil3.transform.RoundedCornersTransformation
import com.bowoon.common.Log
import com.bowoon.common.StorageUtils
import com.bowoon.common.px
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 이미지 로더 모듈
 *
 * 오브젝트로 구현 되어 싱글톤으로 사용할 수 있음
 */
@Singleton
class ImgLoader @Inject constructor(

) {
    companion object {
        private const val TAG = "#ImageLoader"
        const val CACHE_FOLDER_NAME = "image"
        const val MEMORY_CACHE_PERCENT = 0.25
        const val CACHE_BYTES_SIZE = 512L * 1024 * 1024 // 512MB

        fun getImgLoader(context: Context): ImgLoader =
            EntryPoints.get(context.applicationContext, ImgLoaderEntryPoint::class.java)
                .getImgLoader()
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface ImgLoaderEntryPoint {
        fun getImgLoader(): ImgLoader
    }

    /**
     * ImageRequest에 사용될 옴션들 모음
     *
     * placeholder는 transform 적용이 안됨
     * - placeholder와 나타날 이미지의 크기가 동일해야 뷰의 변형을 최소화 할 수 있음
     *
     * @param options 적용시킬 옵션이 담긴 객체
     * @param isCompose 컴포즈에서 호출했는지 확인하는 플래그
     * @return imageRequest 적용 완료된 imageRequest
     */
    private fun ImageRequest.Builder.setOptions(options: ImageOption?, isCompose: Boolean): ImageRequest.Builder? =
        options?.run {
            if (crossFadeDuration > 0) {
                this@setOptions.crossfade(crossFadeDuration)
            }

            placeholderId?.let {
                placeholder(it)
            } ?: run {
                placeholder(placeholder)
            }

            errorId?.let {
                error(it)
            } ?: run {
                error(error)
            }

            if (width != null && width > 0 && height != null && height > 0) {
                size(width, height)
            }

            if (!isCompose) {
                when {
                    radius > 0 -> RoundedCornersTransformation(radius.px.toFloat())
                    topLeft > 0 || topRight > 0 || bottomLeft > 0 || bottomRight > 0 -> {
                        RoundedCornersTransformation(topLeft.px.toFloat(), topRight.px.toFloat(), bottomLeft.px.toFloat(), bottomRight.px.toFloat())
                    }
                    isCircleCropTransformation -> CircleCropTransformation()
                    else -> null
                }?.let { transformations(it) }
            }

            if (!headerMap.isNullOrEmpty()) {
                this@setOptions.httpHeaders(
                    NetworkHeaders.Builder().apply {
                        headerMap.forEach {
                            add(it.key, it.value)
                        }
                    }.build()
                )
            }

            allowHardware(allowHardware)
            diskCachePolicy(diskCachePolicy)
            memoryCachePolicy(memoryCachePolicy)

            this@setOptions
        }

    /**
     * compose 이미지 로더
     *
     * coil에 [AsyncImage] 사용
     *
     * @param modifier 수정자
     * @param options 이미지에 추가될 옵션
     * @param imageRequest 이미지 로더에 사용할 요청 정보들
     * @param contentDescription 이미지 설명
     * @param contentScale 이미지 스케일 타입
     * @param alpha 이미지 투명도
     * @param listener 이미지 상태에 따른 리스너
     */
    @Composable
    fun AsyncImageLoad(
        source: Any?,
        modifier: Modifier = Modifier,
        options: ImageOption? = null,
        imageRequest: ImageRequest.Builder? = null,
        contentDescription: String? = null,
        contentScale: ContentScale = ContentScale.Fit,
        @FloatRange(0.0, 1.0) alpha: Float = DefaultAlpha,
        listener: ImageLoadListener? = null
    ) {
        AsyncImage(
            modifier = modifier,
            model = getImageRequest(LocalContext.current, imageRequest).apply {
                data(source)
                setOptions(options, true)
                if (listener != null) {
                    listener(
                        onCancel = {
                            Log.d(TAG, "compose load cancel > source : [${it.data}]")
                            listener.onCancel(it.data)
                        }
                    )
                }
            }.build(),
            contentDescription = contentDescription,
            alpha = alpha,
            contentScale = contentScale,
            imageLoader = SingletonImageLoader.get(LocalContext.current),
            onError = {
                Log.e(TAG, "compose load error > message : [${it.result.throwable.message}]")
                listener?.onError(it.result.request.data, it.result.throwable)
            },
            onSuccess = {
                Log.d(TAG, "compose load success > source : [${it.result.request.data}], dataSource : [${it.result.dataSource}], width : [${it.result.image.width}], height : [${it.result.image.height}], drawable : [${it.result.image}]")
                listener?.onSuccess(it.result.request.data, it.result.dataSource, it.result.image.asDrawable(Resources.getSystem()))
            }
        )
    }

    /**
     * compose 이미지 로더
     *
     * 로드 상태에 따라 Composable 함수가 필요할 때 사용
     *
     * coil에 [SubcomposeAsyncImage] 사용
     *
     * @param modifier 수정자
     * @param options 이미지에 추가될 옵션
     * @param imageRequest 이미지 로더에 사용할 요청 정보들
     * @param contentDescription 이미지 설명
     * @param contentScale 이미지 스케일 타입
     * @param alpha 이미지 투명도
     * @param error 이미지 요청 실패 컴포즈 함수
     * @param success 이미지 요청 성공 컴포즈 함수
     */
    @Composable
    fun SubComposeAsyncImageLoad(
        source: Any?,
        modifier: Modifier = Modifier,
        options: ImageOption? = null,
        imageRequest: ImageRequest.Builder? = null,
        contentDescription: String? = null,
        error: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Error) -> Unit)? = null,
        success: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Success) -> Unit)? = null,
        contentScale: ContentScale = ContentScale.Fit,
        @FloatRange(0.0, 1.0) alpha: Float = DefaultAlpha
    ) {
        SubcomposeAsyncImage(
            modifier = modifier,
            model = getImageRequest(LocalContext.current, imageRequest).apply {
                data(source)
                setOptions(options, true)
            }.build(),
            contentDescription = contentDescription,
            alpha = alpha,
            contentScale = contentScale,
            imageLoader = SingletonImageLoader.get(LocalContext.current),
            error = {
                Log.e(TAG, "compose load error > message : [${it.result.throwable.message}]")
                it.painter?.let { painter ->
                    Image(painter = painter, contentDescription = contentDescription, alpha = alpha)
                }
                error?.invoke(this, it)
            },
            success = {
                Log.d(TAG, "compose load success > source : [${it.result.request.data}], dataSource : [${it.result.dataSource}], width : [${it.result.image.width}], height : [${it.result.image.height}], drawable : [${it.result.image}]")
                success?.invoke(this, it)
                SubcomposeAsyncImageContent()
            }
        )
    }

    /**
     * View 이미지 로더
     *
     * imageView가 null이면 프리로드 진행
     *
     * @param context context
     * @param source 이미지를 가져올 곳 (url, drawable, drawableID, file)
     * @param imageView 이미지가 들어갈 뷰
     * @param scaleType ImageView 스케일 타입
     * @param options 이미지에 추가될 옵션
     * @param imageRequest 이미지 로더에 사용할 요청 정보들
     * @param listener 이미지 상태에 따른 리스너
     */
    fun load(
        context: Context,
        source: Any?,
        imageView: ImageView?,
        options: ImageOption? = null,
        scaleType: ImageView.ScaleType? = null,
        imageRequest: ImageRequest.Builder? = null,
        listener: ImageLoadListener? = null
    ) {
        getImageRequest(context, imageRequest).run {
            data(source)
            if (imageView != null) {
                if (scaleType != null) {
                    imageView.scaleType = scaleType
                }
                target(imageView)
            }
            setOptions(options, false)
            listener(
                onCancel = { request ->
                    Log.d(TAG, "load cancel")
                    listener?.onCancel(request.data)
                },
                onError = { request, result ->
                    Log.e(TAG, "load error > message : [${result.throwable.message}]")
                    listener?.onError(request.data, result.throwable)
                },
                onStart = { request ->
                    Log.d(TAG, "load start > source : [${request.data}], header : [${request.httpHeaders}]")
                    listener?.onStart(request.data)
                },
                onSuccess = { request, result ->
                    Log.d(TAG, "load success > source : [${request.data}], dataSource : [${result.dataSource}], width : [${result.image.width}], height : [${result.image.height}], drawable : [${result.image}]")
                    listener?.onSuccess(request.data, result.dataSource, result.image.asDrawable(context.resources))
                }
            )
            SingletonImageLoader.get(context).enqueue(this.build())
        }
    }

    /**
     * 이미지 다운로드
     *
     * saveFile로 파일의 경로를 설정
     *
     * @param context context
     * @param source 이미지를 가져올 곳 (url)
     * @param folder 이미지가 저장될 폴더 구분
     * @param fileName 파일 이름
     * @param format 이미지에 적용될 포맷 (jpg, png, webp)
     * @param quality 이미지 뭘리티
     * @param overwrite 파일 덮어쓰기
     * @param imageRequest 이미지 로더에 사용할 요청 정보들
     * @param listener 이미지 상태에 따른 리스너
     */
    suspend fun download(
        context: Context,
        source: String,
        folder: String,
        fileName: String,
        format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
        @IntRange(0, 100) quality: Int = 100,
        overwrite: Boolean = true,
        imageRequest: ImageRequest.Builder? = null,
        listener: ImageLoadListener? = null
    ) {
        val file = when (format) {
            Bitmap.CompressFormat.JPEG -> "jpg"
            Bitmap.CompressFormat.PNG -> "png"
            else -> "webp"
        }.run {
            File(StorageUtils.getCacheDirectory(context, folder), "${fileName}.${this}")
        }

        if (file.exists()) {
            if (overwrite) {
                Log.d(TAG, "imgLoader file download > file is already, delete and download")
                file.delete()
            } else {
                Log.e(TAG, "imgLoader file download > file is already, download failed")
                return
            }
        }

        SingletonImageLoader.get(context).execute(
            getImageRequest(context, imageRequest).data(source)
                .listener(
                    onStart = {
                        listener?.onStart(it.data)
                    },
                    onCancel = {
                        listener?.onCancel(it.data)
                    },
                    onError = { _, result ->
                        listener?.onError(
                            source,
                            result.throwable
                        )
                    },
                    onSuccess = { _, result ->
                        FileOutputStream(file).use { fos ->
                            (result.image.asDrawable(context.resources) as? BitmapDrawable)?.bitmap?.let { bitmap ->
                                bitmap.compress(format, quality, fos)
                                listener?.onSuccess(source, file)
                                listener?.onSuccess(source, result.dataSource, result.image.asDrawable(context.resources))
                                Log.d(
                                    TAG,
                                    "file download success > path : [${file.path}], size : [${file.length()}]"
                                )
                            } ?: run {
                                listener?.onError(
                                    source,
                                    Throwable("bitmap is null")
                                )
                            }
                        }
                    }
                ).build()
        )
    }

    /**
     * 유효한 ImageRequest.Builder 반환
     *
     * @param context context
     * @param imageRequest 전달받은 ImageRequest.Builder
     * @return null이 아닌 ImageRequest 반환
     */
    private fun getImageRequest(
        context: Context,
        imageRequest: ImageRequest.Builder?
    ): ImageRequest.Builder = (imageRequest ?: getDefaultImageRequestBuilder(context))

    /**
     * ImageRequest가 없을 경우 기본적으로 들어가는 ImageRequest
     *
     * @param context context
     * @return ImageRequest.Builder 반환
     */
    private fun getDefaultImageRequestBuilder(context: Context): ImageRequest.Builder =
        ImageRequest.Builder(context)
            .allowHardware(true)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .precision(Precision.EXACT)
}