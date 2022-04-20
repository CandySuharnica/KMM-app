package by.candy.suharnica.core.dataSource.network


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import sqldelight.CatalogItem

class CatalogLoader(
    //private val httpClient: HttpClient
    ) {
    private val client = HttpClient()
    suspend fun getHtml(): List<CatalogItem> {
        return client
            .get("https://syharnica-default-rtdb.europe-west1.firebasedatabase.app/catalog.json")
            .body()
    }
}