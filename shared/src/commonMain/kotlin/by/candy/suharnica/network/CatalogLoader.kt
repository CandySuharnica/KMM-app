package by.candy.suharnica.network


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class CatalogLoader
{
    private val client = HttpClient()
    suspend fun getHtml(): String {
        return client
            .get("https://syharnica-default-rtdb.europe-west1.firebasedatabase.app/catalog.json")
            .body()
    }
}