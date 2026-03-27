package com.isradeleon.kmpappv2.common.network

import com.isradeleon.kmpappv2.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Factory design pattern for the HTTP client.
 *
 * This is implemented this way, because the underlying
 * networking engine may vary from Android to iOS.
 *
 * We can still share common configurations.
 */
object HttpClientFactory {
    fun create(
        engine: HttpClientEngine
    ): HttpClient {
        // Ktor's http client
        return HttpClient(engine) {
            // Install function is Ktor's client configuration function
            install(ContentNegotiation) { // Serialization / deserialization
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }

            // Timeout configuration
            install(HttpTimeout) {
                socketTimeoutMillis = 5_000L
                requestTimeoutMillis = 5_000L
            }

            // Allow Ktor's cache system
            install(HttpCache)

            defaultRequest {
                // Here you would set up the headers
                headers {
                    append(
                        "x-access-token",
                        BuildKonfig.COINRANKING_KEY
                    )
                }

                // Content type
                contentType(ContentType.Application.Json)
            }
        }
    }
}