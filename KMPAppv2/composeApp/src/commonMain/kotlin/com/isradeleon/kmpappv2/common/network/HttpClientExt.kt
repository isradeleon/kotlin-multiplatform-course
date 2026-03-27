package com.isradeleon.kmpappv2.common.network

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Response
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException

/**
 * The reified kotlin keyword, allows you to access the
 * type information of a generic at runtime, which is
 * normally prevented by the JVM.
 *
 * Reified can ONLY be used with inline functions, because
 * the compiler replaces the function call with its actual
 * code at the call site.
 *
 * This process allows the compiler to substitute the generic T
 * with the concrete type, making it available for use.
 */
suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Response<T, FailureDetail.Remote> {
    return try {
        responseToResult(
            execute()
        )
    } catch (e: Exception) {
        when(e) {
            is SocketTimeoutException -> Response.Fail(
                FailureDetail.Remote.TIMEOUT
            )
            is UnresolvedAddressException ->  Response.Fail(
                FailureDetail.Remote.NO_CONNECTION
            )
            else -> {
                Response.Fail(
                    FailureDetail.Remote.UNKNOWN
                )
            }
        }
    }
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Response<T, FailureDetail.Remote> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                Response.Success(response.body<T>())
            } catch(_: Exception) {
                Response.Fail(FailureDetail.Remote.SERIALIZATION)
            }
        }
        408 -> Response.Fail(FailureDetail.Remote.TIMEOUT)
        429 -> Response.Fail(FailureDetail.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> Response.Fail(FailureDetail.Remote.SERVER_ERROR)
        else -> Response.Fail(FailureDetail.Remote.UNKNOWN)
    }
}