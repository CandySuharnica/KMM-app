package by.candy.suharnica.cache.databases

import by.candy.suharnica.cache.DatabaseDriverFactory
import by.candy.suharnica.core.dataSource.database.CandyDatabase
import com.squareup.sqldelight.ColumnAdapter
import sqldelight.CatalogItem
import sqldelight.User

class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val listOfStringsAdapter = object : ColumnAdapter<List<String>, String> {
        override fun decode(databaseValue: String) =
            if (databaseValue.isEmpty()) {
                listOf()
            } else {
                databaseValue.split("!!")
            }

        override fun encode(value: List<String>) = value.joinToString(separator = "!!")
    }
    private val listOfIntAdapter = object : ColumnAdapter<List<Int>, String> {
        override fun decode(databaseValue: String): List<Int> =
            if (databaseValue.isEmpty()) {
                listOf()
            } else {
                databaseValue.split("!!").map { it.toInt() }
            }

        override fun encode(value: List<Int>): String = value.joinToString(separator = "!!")
    }

    private val database = CandyDatabase(
        driver = databaseDriverFactory.createDriver(),
        CatalogItemAdapter = CatalogItem.Adapter(
            imgUrlAdapter = listOfStringsAdapter
        ),
        UserAdapter = User.Adapter(
            likesAdapter = listOfIntAdapter
        )
    )

    val catalogDatabase = CatalogDatabase(database)
    val basketDatabase = BasketDatabase(database)
    val userDatabase = UserDatabase(database)

}