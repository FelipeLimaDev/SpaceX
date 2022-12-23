package com.fdlr.spacex.datasource.cache

import com.codingwithmitch.food2forkkmm.datasource.cache.NewsDbQueries
import com.fdlr.spacex.datasource.network.model.NewDto
import kotlinx.datetime.Clock

class SpaceCacheImpl(
    private val database: SpaceDatabase,
) : SpaceCache {

    private var queries: NewsDbQueries = database.newsDbQueries

    override fun insert(new: NewDto) {
        queries.insertNew(
            id = new.id.toLong(),
            title = new.title,
            url = new.url,
            imageUrl = new.imageUrl,
            newsSite = new.newsSite,
            summary = new.summary,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }

    override fun insert(news: List<NewDto>) {
        for (new in news) {
            insert(new)
        }
    }

    override fun getAll(): List<NewDto> {
        return queries.getAllnews().executeAsList().toNewList()
    }

    override fun get(newId: Int): NewDto? {
        return try {
            queries.getNewById(id = newId.toLong()).executeAsOne().toNew()
        } catch (e: NullPointerException) {
            null
        }
    }
}



