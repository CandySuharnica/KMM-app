package by.candy.suharnica.android.composeUI.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CandyColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val controlColor: Color,
    val errorColor: Color
)

enum class JetHabitStyle {
    Purple, Orange, Blue, Red, Green
}

val LocalCandyColors = staticCompositionLocalOf<CandyColors> {
    error("No colors provided")
}