package by.candy.suharnica.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserItem(
    val id: Int = 0,
    val uid: String = "",
    val url_avatar: String = "",
    @SerialName("name")
    val first_name: String = "",
    val second_name: String = "",
    val mobile_number: String = "",
    val address: String = "",
    @SerialName("likes_id")
    var item_likes: List<Int> = emptyList(),
)
