package com.fdlr.spacex.datasource.cache

import com.codingwithmitch.food2forkkmm.datasource.cache.New_Entity
import com.fdlr.spacex.datasource.network.model.NewDto
import com.squareup.sqldelight.db.SqlDriver


class SpaceDatabaseFactory(
    private val driverFactory: DriverFactory
){
    fun createDatabase(): SpaceDatabase {
        return SpaceDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
   fun createDriver(): SqlDriver
}

fun New_Entity.toNew(): NewDto {
    return NewDto(
        id = id.toInt(),
        title = title,
        url = url,
        imageUrl = imageUrl,
        newsSite = newsSite,
        summary = summary
    )
}

fun List<New_Entity>.toNewList(): List<NewDto>{
    return map{it.toNew()}
}













