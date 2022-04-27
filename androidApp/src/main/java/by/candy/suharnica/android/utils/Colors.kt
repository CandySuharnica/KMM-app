package by.candy.suharnica.android.utils

import androidx.compose.ui.graphics.Color

sealed class Colors(
    val color: Color
) {
    object RedSale : Colors(Color(0x80FF5F3C))
    object RedButton : Colors(Color(0xFFFF6B4B))
}