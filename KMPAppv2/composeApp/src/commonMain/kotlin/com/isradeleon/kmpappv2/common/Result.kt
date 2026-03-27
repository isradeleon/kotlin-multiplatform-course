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
sealed interface Result<out D, out F: Failure> {
    /**
     * Successful result.
     * Nothing as the error type which represents a value that NEVER exists.
     * When we have a successful result, there's no erro to consider (Nothing).
     */
    data class Success<out D>(val data: D): Result<D, Nothing>

    /**
     * Failure result.
     * Similarly, we represent Nothing as the success data,
     * since here it doesn't exist.
     */
    data class Fail<out F: Failure>(val error: F): Result<Nothing, F>
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
inline fun <A, F: Failure, B> Result<A, F>.map(transform: (A) -> B): Result<B, F> {
    return when(this) {
        is Result.Success -> Result.Success(transform(data))
        is Result.Fail -> this
    }
}

/**
 * Typealias for empty results.
 */
typealias EmptyResult<F> = Result<Unit, F>
fun <T, F: Failure> Result<T, F>.asEmptyResult(): EmptyResult<F> {
    return map {}
}

/**
 * On success callback.
 */
inline fun <T, F: Failure> Result<T,F>.onSuccess(
    action: (T) -> Unit
): Result<T, F> {
    return when(this) {
        is Result.Success -> {
            action(data)
            this
        }
        is Result.Fail -> this
    }
}

/**
 * On fail callback.
 */
inline fun <T, F: Failure> Result<T,F>.onFail(
    action: (F) -> Unit
): Result<T, F> {
    return when(this) {
        is Result.Fail -> {
            action(error)
            this
        }
        is Result.Success -> this
    }
}