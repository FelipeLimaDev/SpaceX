package com.fdlr.spacex.datasource.network.services

import com.fdlr.spacex.datasource.network.SpaceXError
import com.fdlr.spacex.datasource.network.SpaceXException
import com.fdlr.spacex.datasource.network.model.NewDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*

class SpaceXServiceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : SpaceXService {

    override suspend fun getNews(
        limit: Int,
        start: Int
    ): List<NewDto> {

        val result = try {
            httpClient.get {
                url("$baseUrl/v3/articles?_limit=$limit&_start=$start")
                contentType(ContentType.Application.Json)
            }
        } catch (e: IOException) {
            throw SpaceXException(SpaceXError.SERVICE_UNAVAILABLE)
        }

        when (result.status.value) {
            in 200..299 -> Unit
            500 -> throw SpaceXException(SpaceXError.SERVER_ERROR)
            in 400..499 -> throw SpaceXException(SpaceXError.CLIENT_ERROR)
            else -> throw SpaceXException(SpaceXError.UNKNOWN_ERROR)
        }

        return try {
            result.body<List<NewDto>>()
        } catch (e: Exception) {
            throw SpaceXException(SpaceXError.SERVER_ERROR)
        }
    }

    companion object {
        const val BASE_URL = "https://api.spaceflightnewsapi.net"
    }

}








