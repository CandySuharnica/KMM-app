package by.candy.suharnica

import by.candy.suharnica.cache.CatalogDatabase
import by.candy.suharnica.cache.DatabaseDriverFactory
import by.candy.suharnica.entity.CatalogItem
import by.candy.suharnica.network.CandySuharnicaAPI
import kotlinx.coroutines.flow.*

class CandySDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = CatalogDatabase(databaseDriverFactory)
    private val api = CandySuharnicaAPI()

    @Throws(Exception::class)
    fun getCatalogList(): Flow<List<CatalogItem>> {
        val cachedLaunches = database.getAllLaunches()
        return flow{
            emit(cachedLaunches.first())
            val catalogItems = api.getAllLaunches()

            cachedLaunches.onEmpty {
                   // database.clearDatabase()
                    database.fillDB(items = catalogItems)
            }
            emit(catalogItems)
         }

    }

}