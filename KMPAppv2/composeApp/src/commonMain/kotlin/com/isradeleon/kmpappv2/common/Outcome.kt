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
sealed interface Outcome<out D, out F: FailureDetail> {
    /**
     * Successful result.
     * Nothing as the error type which represents a value that NEVER exists.
     * When we have a successful result, there's no erro to consider (Nothing).
     */
    data class Success<out D>(val data: D): Outcome<D, Nothing>

    /**
     * Failure result.
     * Similarly, we represent Nothing as the success data,
     * since here it doesn't exist.
     */
    data class Failure<out F: FailureDetail>(val error: F): Outcome<Nothing, F>
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
inline fun <A, F: FailureDetail, B> Outcome<A, F>.map(transform: (A) -> B): Outcome<B, F> {
    return when(this) {
        is Outcome.Success -> Outcome.Success(transform(data))
        is Outcome.Failure -> this
    }
}

/**
 * Typealias for empty results.
 */
typealias EmptyResult<F> = Outcome<Unit, F>
fun <T, F: FailureDetail> Outcome<T, F>.asEmptyResult(): EmptyResult<F> {
    return map {}
}

/**
 * On success callback.
 */
inline fun <T, F: FailureDetail> Outcome<T,F>.onSuccess(
    action: (T) -> Unit
): Outcome<T, F> {
    return when(this) {
        is Outcome.Success -> {
            action(data)
            this
        }
        is Outcome.Failure -> this
    }
}

/**
 * On fail callback.
 */
inline fun <T, F: FailureDetail> Outcome<T,F>.onFail(
    action: (F) -> Unit
): Outcome<T, F> {
    return when(this) {
        is Outcome.Failure -> {
            action(error)
            this
        }
        is Outcome.Success -> this
    }
}