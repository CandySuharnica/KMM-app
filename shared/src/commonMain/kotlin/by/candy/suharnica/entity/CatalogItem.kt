package by.candy.suharnica.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatalogItem(
    val id: Long = 0,
    @SerialName("name")
    val label: String = "",
    val type: String = "", //for filter in the groups
    val weight: Int = 0,
    val imgUrl: List<String> = emptyList(),
    val price: Double = 0.0,
    val priceSale: Double = 0.0,
    var likes: Int = 0,
    var about: String = "",
    @SerialName("product_composition")
    var productComposition: String = "",
    var calorie: String = "",
    var carbohydrates: String = "",
    var fats: String = "",
    var protein: String = "",
    //var isLiked: Boolean = false
)
