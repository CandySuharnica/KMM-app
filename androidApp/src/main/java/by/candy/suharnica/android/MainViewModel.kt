package by.candy.suharnica.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.candy.suharnica.CandySDK
import by.candy.suharnica.cache.databases.OnBasketMode
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import sqldelight.ResponseItemFromId

typealias BasketItem = ResponseItemFromId

class MainViewModel(
    private val candySdk: CandySDK
) : ViewModel() {

    val errorHandler = MutableSharedFlow<String>()

    private val firebaseAuth = FirebaseAuth.getInstance()

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


    val userFlow = MutableSharedFlow<FirebaseUser>()


    fun login(email: String, password: String) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { result ->
                    viewModelScope.launch {
                        if (result.isSuccessful) {
                            firebaseAuth.currentUser?.let { userFlow.emit(it) }
                        } else errorHandler.emit(result.exception?.localizedMessage ?: "some error")
                    }
                }
        } catch (e: Exception) {
            viewModelScope.launch {
                errorHandler.emit(e.localizedMessage ?: "some error")
            }
        }
    }

    fun register(email: String, password: String) {
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                viewModelScope.launch {
                    if (it.isSuccessful) {
                        firebaseAuth.currentUser?.let { userFlow.emit(it) }
                    } else errorHandler.emit(it.exception?.localizedMessage ?: "some error")
                }
            }
        } catch (e: Exception) {
            viewModelScope.launch {
                errorHandler.emit(e.localizedMessage ?: "some error")
            }
        }
    }

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser


    class Factory(private val candySdk: CandySDK) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(candySdk) as T
        }
    }
}