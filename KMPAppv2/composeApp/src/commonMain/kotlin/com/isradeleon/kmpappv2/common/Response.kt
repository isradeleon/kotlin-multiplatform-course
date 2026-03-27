package com.isradeleon.kmpappv2.common

/**
 * Sealed class guarantees all inherit classes
 * are known at compile time,
 * allowing for exhausting when statements (cases).
 *
 * The out keyword tells the compiler that these types are
 * only produced and not consumed. This allows us to safely
 * substitute subtypes.
 */
sealed interface Response<out D, out F: Failure> {
    /**
     * Successful result.
     * Nothing as the error type which represents a value that NEVER exists.
     * When we have a successful result, there's no erro to consider (Nothing).
     */
    data class Success<out D>(val data: D): Response<D, Nothing>

    /**
     * Failure result.
     * Similarly, we represent Nothing as the success data,
     * since here it doesn't exist.
     */
    data class Fail<out F: Failure>(val error: F): Response<Nothing, F>
}

/**
 * Inline fun to transform the incoming value to the desired one.
 *
 * Inline tells the compiler to copy the function's body to the call site,
 * instead of creating a regular function call.
 *
 * Inline keyword is used for compiler optimization &
 * performance improvements, specially with lambdas &
 * high order functions.
 */
inline fun <A, F: Failure, B> Response<A, F>.map(transform: (A) -> B): Response<B, F> {
    return when(this) {
        is Response.Success -> Response.Success(transform(data))
        is Response.Fail -> this
    }
}

/**
 * Typealias for empty results.
 */
typealias EmptyResult<F> = Response<Unit, F>
fun <T, F: Failure> Response<T, F>.asEmptyResult(): EmptyResult<F> {
    return map {}
}

/**
 * On success callback.
 */
inline fun <T, F: Failure> Response<T,F>.onSuccess(
    action: (T) -> Unit
): Response<T, F> {
    return when(this) {
        is Response.Success -> {
            action(data)
            this
        }
        is Response.Fail -> this
    }
}

/**
 * On fail callback.
 */
inline fun <T, F: Failure> Response<T,F>.onFail(
    action: (F) -> Unit
): Response<T, F> {
    return when(this) {
        is Response.Fail -> {
            action(error)
            this
        }
        is Response.Success -> this
    }
}