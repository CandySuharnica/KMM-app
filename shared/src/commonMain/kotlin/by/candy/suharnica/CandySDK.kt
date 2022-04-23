package by.candy.suharnica

import by.candy.suharnica.cache.DatabaseDriverFactory
import by.candy.suharnica.cache.databases.Database
import by.candy.suharnica.entity.CatalogItem
import by.candy.suharnica.network.CandySuharnicaAPI
import kotlinx.coroutines.flow.*

class CandySDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = CandySuharnicaAPI()

    @Throws(Exception::class)
    fun getCatalogList(): Flow<List<CatalogItem>> {

        return flow{
            val cachedLaunches = database.catalogDatabase.getAllLaunches().first()

            //emit(cachedLaunches.first())

            val catalogItems = api.getAllLaunches()

            if (cachedLaunches.isEmpty()){
                database.catalogDatabase.fillDB(items = catalogItems)
            }
            /*cachedLaunches.onEmpty {
                   // database.clearDatabase()

            }*/
            emit(catalogItems)
         }

    }

    fun getItemFromId(id:Long) = database.catalogDatabase.getItemFromId(id)

    fun getBasket() = database.basketDatabase.getBasket()

    fun addItem(id: Long) {
        database.basketDatabase.addItemIntoDatabase(id)
    }
}