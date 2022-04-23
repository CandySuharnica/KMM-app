package by.candy.suharnica.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.candy.suharnica.CandySDK
import sqldelight.ResponseItemFromId

typealias BasketItem = ResponseItemFromId

class MainViewModel(
   private val candySdk: CandySDK
) : ViewModel() {

    val catalogList = candySdk.getCatalogList()

    fun getItemFromId(id:Long) = candySdk.getItemFromId(id)

    fun getBasket():List<BasketItem> = candySdk.getBasket()

    fun addItemIntoBasket(id: Long) {
        candySdk.addItem(id)
    }

    class Factory(private val candySdk: CandySDK) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(candySdk) as T
        }
    }
}