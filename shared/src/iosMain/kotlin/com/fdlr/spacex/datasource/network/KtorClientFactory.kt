package com.fdlr.spacex.datasource.network

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

actual class KtorClientFactory {
    actual fun build(): HttpClient {
        return HttpClient(Darwin) {
            install(HttpRequestRetry)
            install(ContentNegotiation) {
                json()
            }
        }
    }
}
