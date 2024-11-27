package com.bowoon.common

import app.cash.turbine.test
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ResultKtTest {
    @Test
    fun flow_result_test() = runTest {
        flow {
            emit(1)
            throw Exception("테스트 완료")
        }
            .asResult()
            .test {
                assertEquals(Result.Loading, awaitItem())
                assertEquals(Result.Success(1), awaitItem())

                when (val errorResult = awaitItem()) {
                    is Result.Error -> assertEquals(
                        "테스트 완료",
                        errorResult.exception.message,
                    )
                    Result.Loading,
                    is Result.Success,
                    -> throw IllegalStateException(
                        "예상치 못한 오류가 발생했습니다.",
                    )
                }

                awaitComplete()
            }
    }
}