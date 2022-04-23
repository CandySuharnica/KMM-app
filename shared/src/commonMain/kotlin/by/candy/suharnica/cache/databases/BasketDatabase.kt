package by.candy.suharnica.cache.databases

import by.candy.suharnica.core.dataSource.database.CandyDatabase
import sqldelight.ResponseItemFromId

class BasketDatabase(database: CandyDatabase) {

    private val dbQuery = database.basketItemQueries

    fun addItemIntoDatabase(idItem:Long){
        dbQuery.insertItem(idItem,1)
    }

    fun getBasket() : List<ResponseItemFromId>{
      return  dbQuery.responseItemFromId().executeAsList()
    }
}