package by.candy.suharnica

import by.candy.suharnica.cache.DatabaseDriverFactory
import by.candy.suharnica.cache.databases.Database
import by.candy.suharnica.cache.databases.OnBasketMode
import by.candy.suharnica.entity.CatalogItem
import by.candy.suharnica.network.CandySuharnicaAPI
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrDefault
import kotlinx.coroutines.flow.*
import sqldelight.GetTotalCount
import sqldelight.GetTotalPrice

class CandySDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = CandySuharnicaAPI()

    private val flow = flow {
        emit(database.basketDatabase.getTotalCount())
    }


    @Throws(Exception::class)
    fun getCatalogList(): Flow<List<CatalogItem>> {

        return flow {
            val cachedLaunches = database.catalogDatabase.getAllLaunches().first()

            val catalogItems = api.getAllLaunches()

            if (cachedLaunches.isNotEmpty())
                catalogItems.ifEmpty {
                    cachedLaunches
                }
            else database.catalogDatabase.fillDB(items = catalogItems)

            emit(catalogItems)
        }

    }

    fun getItemFromId(id: Long) = database.catalogDatabase.getItemFromId(id)

    fun getBasket() = database.basketDatabase.getBasket()

    suspend fun addItem(id: Long, mode: OnBasketMode) {
        database.basketDatabase.onItemIntoDatabase(id, mode)
    }

    fun getCount(idItem: Long) = database.basketDatabase.getCount(idItem)

    fun getTotalCount() = flow
    fun getTotalPrice() = database.basketDatabase.getTotalPrice()
    fun getTotalWeight() = database.basketDatabase.getTotalWeight()

}