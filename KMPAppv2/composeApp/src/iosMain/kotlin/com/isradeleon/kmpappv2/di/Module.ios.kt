package com.isradeleon.kmpappv2.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

/**
 * iOS "actual" platform specific implementation for HTTP.
 */
actual val platformModule = module {
    single<HttpClientEngine> {
        /**
         * This method from Ktor
         * creates an HttpClientEngine
         * for iOS.
         */
        Darwin.create()
    }
}