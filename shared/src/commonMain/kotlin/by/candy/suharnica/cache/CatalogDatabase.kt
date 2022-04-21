package by.candy.suharnica.cache

import by.candy.suharnica.core.dataSource.database.CandyDatabase
import by.candy.suharnica.entity.CatalogItem
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatalogDatabase(databaseDriverFactory: DatabaseDriverFactory) {

    private val listOfStringsAdapter = object : ColumnAdapter<List<String>, String> {
        override fun decode(databaseValue: String) =
            if (databaseValue.isEmpty()) {
                listOf()
            } else {
                databaseValue.split("!!")
            }

        override fun encode(value: List<String>) = value.joinToString(separator = "!!")
    }

    private val database = CandyDatabase(
        driver = databaseDriverFactory.createDriver(),
        CatalogItemAdapter = sqldelight.CatalogItem.Adapter(
            imgUrlAdapter = listOfStringsAdapter,
            productCompositionAdapter = listOfStringsAdapter,
            nutritionalValueAdapter = listOfStringsAdapter
        )
    )

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
                    id = it.id.toLong(),
                    label = it.label,
                    type = it.type,
                    weight = it.weight,
                    imgUrl = it.imgUrl,
                    price = it.price,
                    priceSale = it.priceSale,
                    likes = it.likes,
                    about = it.about,
                    productComposition = it.productComposition,
                    nutritionalValue = it.nutritionalValue
                )
            }
        }
    }

    internal fun getAllLaunches(): List<CatalogItem> {
        return dbQuery.getAll(::mapCatalog).executeAsList()
    }

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
        productComposition: List<String>,
        nutritionalValue: List<String>
    ): CatalogItem {
        return CatalogItem(
            id = id.toInt(),
            label = label,
            type = type,
            weight = weight,
            imgUrl = imgUrl,
            price = price,
            priceSale = priceSale,
            likes = likes,
            about = about,
            productComposition = productComposition,
            nutritionalValue = nutritionalValue
        )
    }
}