package com.isradeleon.kmpappv2.presentation.coin_details_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.isradeleon.kmpappv2.theme.LocalCoinRoutineColorsPalette

@Composable
fun PerformanceChart(
    modifier: Modifier,
    nodes: List<Double>
) {
    if (nodes.isEmpty()) return

    val max = nodes.maxOrNull() ?: return
    val min = nodes.minOrNull() ?: return
    val lineColor = if (nodes.last() > nodes.first())
            LocalCoinRoutineColorsPalette.current.profitGreen
        else
            LocalCoinRoutineColorsPalette.current.lossRed

    Canvas(
        modifier = Modifier.fillMaxWidth()
    ) {
        /**
         * The path object represents drawing commands.
         */
        val path = Path()
        nodes.forEachIndexed { index, value ->
            val x = index * (size.width / (nodes.size - 1))
            val y = size.height * (1 - ((value - min) / (max - min)).toFloat())

            // Drawing commands sent to Path.
            if (index == 0)
                path.moveTo(x, y)
            else
                path.lineTo(x, y)
        }

        /**
         * This function triggers the drawing of the path.
         */
        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(width = 3.dp.toPx())
        )
    }
}