package by.candy.suharnica.cache.databases

import by.candy.suharnica.core.dataSource.database.CandyDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrDefault
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext
import sqldelight.*

class BasketDatabase(database: CandyDatabase) {

    private val dbQuery = database.basketItemQueries

    internal suspend fun onItemIntoDatabase(idItem: Long, mode: OnBasketMode) {
        withContext(Dispatchers.Default) {
            val item = getItemBasketFromId(idItem)
            if (mode == OnBasketMode.ADD && item != null)
                updateBasketItem(item.count + 1, idItem)
            else if (mode == OnBasketMode.ADD && item == null)
                insertBasketItem(1, idItem)
            else if (mode == OnBasketMode.REMOVE && item!!.count == 1)
                removeBasketItem(idItem)
            else
                updateBasketItem(item!!.count - 1, idItem)
        }
    }

    internal fun getBasket(): Flow<List<ResponseItemFromId>> {
        return dbQuery.responseItemFromId().asFlow().mapToList()
    }

    /* Maybe you think why we need this function because we have getCount()
    * but if we try call getCount with not existing element we have got error
    * we need observable element in ViewModel logic */
    private fun getItemBasketFromId(idItem: Long): BasketItem? {
        return dbQuery.getItemFromId(idItem).executeAsOneOrNull()
    }

    private fun updateBasketItem(count: Int, idItem: Long) {
        dbQuery.updateItem(count, idItem)
    }

    private fun insertBasketItem(count: Int, idItem: Long) {
        dbQuery.insertItem(idItem, count)
    }

    private fun removeBasketItem(idItem: Long) {
        dbQuery.removeItem(idItem)
    }

    internal fun getCount(idItem: Long): Flow<Int> {
        return dbQuery.getCountFromId(idItem).asFlow().mapToOneOrDefault(0)
    }

    /*internal fun getTotalCount(): Flow<Int> {
        return dbQuery.getTotalCount().asFlow().mapToOneOrDefault(defaultValue = GetTotalCount(0))
            .mapNotNull { it.SUM }
    }

    internal fun getTotalPrice(): Flow<Double> {
        return dbQuery.getTotalPrice().asFlow().mapToOneOrDefault(defaultValue = GetTotalPrice(0.0))
            .mapNotNull { it.SUM }
    }

    internal fun getTotalWeight(): Flow<Int> {
        return dbQuery.getTotalWeight().asFlow()
            .mapToOneOrDefault(defaultValue = GetTotalWeight(0)).mapNotNull { it.SUM }
    }*/
}

enum class OnBasketMode {
    ADD, REMOVE
}