package com.fdlr.spacex.datasource.cache

import com.fdlr.spacex.datasource.network.model.NewDto


interface SpaceCache {

    fun insert(new: NewDto)

    fun insert(news: List<NewDto>)

    fun getAll(): List<NewDto>

    @Throws(NullPointerException::class)
    fun get(newId: Int): NewDto?
}