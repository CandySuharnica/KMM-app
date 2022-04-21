package by.candy.suharnica.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatalogItem(
    val id: Int = 0,
    val label: String = "",
    val type: String = "", //for filter in the groups
    val weight: Int = 0,
    val imgUrl: List<String> = emptyList(),
    val price: Double = 0.0,
    val priceSale: Double = 0.0,
    var likes: Int = 0,
    var about: String = "",
    @SerialName("product_composition")
    var productComposition: List<String> = emptyList(),
    @SerialName("nutritional_value")
    var nutritionalValue: List<String> = emptyList(),
    //var isLiked: Boolean = false
)
