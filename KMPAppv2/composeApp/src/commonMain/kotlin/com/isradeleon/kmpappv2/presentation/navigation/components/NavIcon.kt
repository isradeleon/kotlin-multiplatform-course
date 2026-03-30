package com.isradeleon.kmpappv2.presentation.navigation.components

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ColorFilter
import com.isradeleon.kmpappv2.presentation.navigation.Screen
import com.isradeleon.kmpappv2.presentation.navigation.toNavigationIcon
import org.jetbrains.compose.resources.painterResource

@Composable
fun NavIcon(screen: Screen) {
    Image(
        painterResource(screen.toNavigationIcon()),
        contentDescription = "${screen.title} Tab",
        colorFilter = ColorFilter.tint(
            MaterialTheme.colorScheme.onBackground
        )
    )
}