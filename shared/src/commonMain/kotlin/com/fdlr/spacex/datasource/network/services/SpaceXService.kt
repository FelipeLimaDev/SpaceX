package com.fdlr.spacex.datasource.network.services

import com.fdlr.spacex.datasource.network.model.NewDto


interface SpaceXService {

    suspend fun getNews(limit: Int, start: Int): List<NewDto>
}