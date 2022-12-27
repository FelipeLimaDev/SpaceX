package com.fdlr.spacex.datasource.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual class KtorClientFactory {
    actual fun build(): HttpClient {
        return HttpClient(Android) {
            install(HttpRequestRetry)
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true // ignore extra fields from the server that you not mapped
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }
}

