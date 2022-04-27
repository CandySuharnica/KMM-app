package by.candy.suharnica.network

import by.candy.suharnica.entity.CatalogItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*

class CandySuharnicaAPI {

    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getAllLaunches(): List<CatalogItem> {
        val response = httpClient.get(LAUNCHES_ENDPOINT)
        return if (response.status.isSuccess()) response.body()
        else emptyList()
    }

    companion object {
        private const val LAUNCHES_ENDPOINT =
            "https://syharnica-default-rtdb.europe-west1.firebasedatabase.app/catalog.json"
    }
}