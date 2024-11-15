package com.bowoon.imgloader

import android.graphics.drawable.Drawable
import coil3.decode.DataSource
import java.io.File

/**
 * 이미지 로드 리스너
 */
interface ImageLoadListener {
    /**
     * 이미지 요청이 취소 되었을 때
     *
     * @param source 이미지 파일 출처
     */
    fun onCancel(source: Any) {}

    /**
     * 이미지 요청이 실패 했을 때
     *
     * @param source 이미지 파일 출처
     * @param throwable 에러 내용
     */
    fun onError(source: Any, throwable: Throwable)

    /**
     * 이미지 로드 요청 시작
     *
     * @param source 이미지 파일 출처
     */
    fun onStart(source: Any)

    /**
     * 이미지 요청 성공 했을 때
     *
     * @param source 이미지 파일 출처
     * @param dataSource 이미지 로드 위치
     * @param drawable 이미지
     */
    fun onSuccess(source: Any, dataSource: DataSource, drawable: Drawable)

    /**
     * 파일 다운로드 성공시에 사용하는 리스너
     *
     * @param source 이미지 파일 출처
     * @param file 다운로드가 완료되어 저장된 파일
     */
    fun onSuccess(source: Any, file: File) {}
}