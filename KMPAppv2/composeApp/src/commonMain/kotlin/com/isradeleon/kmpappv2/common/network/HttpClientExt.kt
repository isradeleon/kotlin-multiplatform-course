package com.isradeleon.kmpappv2.common.network

import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

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
) {

    coroutineContext.ensureActive()
}