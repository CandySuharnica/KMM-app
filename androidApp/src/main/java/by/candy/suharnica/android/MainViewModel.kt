package by.candy.suharnica.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.candy.suharnica.CandySDK


class MainViewModel(
   private val candySdk: CandySDK
) : ViewModel() {

    val catalogList = candySdk.getCatalogList()

    fun getItemFromId(id:Long) = candySdk.getItemFromId(id)

    class Factory(private val candySdk: CandySDK) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(candySdk) as T
        }
    }
}