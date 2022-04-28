package by.candy.suharnica.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.candy.suharnica.CandySDK
import by.candy.suharnica.cache.databases.OnBasketMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import sqldelight.ResponseItemFromId

typealias BasketItem = ResponseItemFromId

class MainViewModel(
    private val candySdk: CandySDK
) : ViewModel() {

    fun catalogList(type: String, sort: String) = candySdk.getCatalogList(
        type = type,
        sort = sort
    )

    val sortFlow = MutableStateFlow("Все")
    val searchFlow = MutableStateFlow("")

    val getBasket: Flow<List<BasketItem>> = candySdk.getBasket()

    /* val totalCount = candySdk.getTotalCount()
     val totalPrice = candySdk.getTotalPrice()
     val totalWeight = candySdk.getTotalWeight()*/

    fun getItemFromId(id: Long) = candySdk.getItemFromId(id)

    fun getItemCountInBasket(idItem: Long) = candySdk.getCount(idItem)

    fun addItemIntoBasket(id: Long, mode: OnBasketMode) {
        viewModelScope.launch {
            candySdk.addItem(id, mode)
        }
    }

    val getTypes = candySdk.getTypes()

    class Factory(private val candySdk: CandySDK) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(candySdk) as T
        }
    }
}