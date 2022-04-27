package by.candy.suharnica.android.composeUI.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun MainTheme(
    style: JetHabitStyle = JetHabitStyle.Purple,
    //textSize: JetHabitSize = JetHabitSize.Medium,
    //paddingSize: JetHabitSize = JetHabitSize.Medium,
    //corners: JetHabitCorners = JetHabitCorners.Rounded,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                JetHabitStyle.Purple -> purpleDarkPalette
                JetHabitStyle.Blue -> blueDarkPalette
                JetHabitStyle.Orange -> orangeDarkPalette
                JetHabitStyle.Red -> redSaleBgDarkPalette
                JetHabitStyle.Green -> greenDarkPalette
            }
        }
        false -> {
            when (style) {
                JetHabitStyle.Purple -> purpleLightPalette
                JetHabitStyle.Blue -> blueLightPalette
                JetHabitStyle.Orange -> orangeLightPalette
                JetHabitStyle.Red -> redSaleBgLightPalette
                JetHabitStyle.Green -> greenLightPalette
            }
        }
    }
    CompositionLocalProvider(
        LocalCandyColors provides colors,
        //LocalJetHabitTypography provides typography,
        //LocalJetHabitShape provides shapes,
        //LocalJetHabitImage provides images,
        content = content
    )
}