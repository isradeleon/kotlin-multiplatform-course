package com.isradeleon.kmpappv2.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class KMPAppV2ColorsPalette(
    val profitGreen: Color = Color.Unspecified,
    val lossRed: Color = Color.Unspecified,
)

val ProfitGreenColor = Color(color = 0xFF32de84)
val LossRedColor = Color(color = 0xFFD2122E)

val DarkProfitGreenColor = Color(color = 0xFF32de84)
val DarkLossRedColor = Color(color = 0xFFD2122E)

val LightCoinRoutineColorsPalette = KMPAppV2ColorsPalette(
    profitGreen = ProfitGreenColor,
    lossRed = LossRedColor,
)

val DarkCoinRoutineColorsPalette = KMPAppV2ColorsPalette(
    profitGreen = DarkProfitGreenColor,
    lossRed = DarkLossRedColor,
)

/**
 * Provides data to the entire composable hierarchy
 * without passing values around.
 * */
val LocalCoinRoutineColorsPalette = compositionLocalOf { KMPAppV2ColorsPalette() }