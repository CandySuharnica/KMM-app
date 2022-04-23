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

        return flow{
            val cachedLaunches = database.getAllLaunches().first()
            //emit(cachedLaunches.first())

            val catalogItems = api.getAllLaunches()

            if (cachedLaunches.isEmpty()){
                database.fillDB(items = catalogItems)
            }
            /*cachedLaunches.onEmpty {
                   // database.clearDatabase()

            }*/
            emit(catalogItems)
         }

    }

    fun getItemFromId(id:Long) = database.getItemFromId(id)

}