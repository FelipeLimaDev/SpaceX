package com.fdlr.spacex.datasource.network

import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}
