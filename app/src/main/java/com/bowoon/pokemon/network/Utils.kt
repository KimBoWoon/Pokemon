package com.bowoon.pokemon.network

import retrofit2.HttpException

fun Throwable.getHttpErrorMessage(): String =
    when ((this as? HttpException)?.code()) {
        in 400 .. 499 -> "네트워크에 문제가 있습니다.\n잠시후에 다시 시도해주세요."
        in 500 .. 599 -> "서버에 문제가 있습니다.\n잠시후에 다시 시도해주세요."
        else -> "네트워크에 문제가 있습니다.\n잠시후에 다시 시도해주세요."
    }