package by.candy.suharnica

import by.candy.suharnica.cache.CatalogDatabase
import by.candy.suharnica.cache.DatabaseDriverFactory
import by.candy.suharnica.entity.CatalogItem
import by.candy.suharnica.network.CandySuharnicaAPI

class CandySDK (databaseDriverFactory: DatabaseDriverFactory) {
    private val database = CatalogDatabase(databaseDriverFactory)
    private val api = CandySuharnicaAPI()

    @Throws(Exception::class)
    suspend fun getCatalogList(/*forceReload: Boolean*/): List<CatalogItem> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() /*&& !forceReload*/) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.fillDB(it)
            }
        }
    }
}