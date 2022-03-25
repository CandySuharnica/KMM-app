package by.candy.suharnica.repository

import by.candy.suharnica.CatalogItem
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.app
import dev.gitlive.firebase.database.FirebaseDatabase
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class FirebaseCatalogRepo {

    private val database: FirebaseDatabase = Firebase.database(Firebase.app)
    private val path = "https://syharnica-default-rtdb.europe-west1.firebasedatabase.app/"
    private val database2 = Firebase.database(Firebase.app, path).reference("catalog")


    suspend fun fetchStatusDetail() {
        var item = database2.valueEvents
        val dbRef = database2.valueEvents.map {data ->
            data.value(CatalogItem.serializer())
        }


    }

    fun seeItem(item:CatalogItem){
        val a = item
    }
}
