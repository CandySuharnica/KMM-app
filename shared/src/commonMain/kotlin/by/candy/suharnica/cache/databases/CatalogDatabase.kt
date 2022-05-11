package by.candy.suharnica.cache.databases

import by.candy.suharnica.core.dataSource.database.CandyDatabase
import by.candy.suharnica.entity.CatalogItem
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CatalogDatabase(database: CandyDatabase) {

    private val dbQuery = database.catalogItemQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllItems()
        }
    }

    internal suspend fun fillDB(items: List<CatalogItem>) {
        withContext(Dispatchers.Default) {
            items.forEach {
                dbQuery.insertItem(
                    id = it.id,
                    label = it.label,
                    type = it.type,
                    weight = it.weight,
                    imgUrl = it.imgUrl,
                    price = it.price,
                    priceSale = it.priceSale,
                    likes = it.likes,
                    about = it.about,
                    productComposition = it.productComposition,
                    calorie = it.calorie,
                    carbohydrates = it.carbohydrates,
                    fats = it.fats,
                    protein = it.protein,
                )
            }
        }
    }

    internal fun getAllItems(type: String, sort: String): Flow<List<CatalogItem>> {
        return dbQuery.getAll(type = type,sort = sort, mapper = ::mapCatalog).asFlow().mapToList()
    }

    internal fun getItemFromId(id: Long) = dbQuery.getItemFromId(id, mapper = ::mapCatalog).executeAsOne()

    internal fun remove(id: Long) = dbQuery.remove(id)

    internal fun getTypes() = dbQuery.getTypes().asFlow().mapToList()

    private fun mapCatalog(
        id: Long,
        label: String,
        type: String,
        weight: Int,
        imgUrl: List<String>,
        price: Double,
        priceSale: Double,
        likes: Int,
        about: String,
        productComposition: String,
        calorie: String,
        carbohydrates: String,
        fats: String,
        protein: String
    ): CatalogItem {
        return CatalogItem(
            id = id,
            label = label,
            type = type,
            weight = weight,
            imgUrl = imgUrl,
            price = price,
            priceSale = priceSale,
            likes = likes,
            about = about,
            productComposition = productComposition,
            calorie = calorie,
            carbohydrates = carbohydrates,
            fats = fats,
            protein = protein
        )
    }
}