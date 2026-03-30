package com.isradeleon.kmpappv2.presentation.navigation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.isradeleon.kmpappv2.presentation.navigation.Screen
import com.isradeleon.kmpappv2.presentation.navigation.toNavigationIcon

@Composable
fun MyBottomNavbar(
    navItems: List<Screen>,
    currentNavItem: Screen?,
    onNavItemClicked: (Screen) -> Unit
) {
    NavigationBar {
        navItems.forEach {
            NavigationBarItem(
                label = {
                    Text(it.title)
                },
                icon = {
                    Icon(
                        imageVector = it.toNavigationIcon(),
                        contentDescription = it.title
                    )
                },
                selected = it == currentNavItem,
                onClick = {
                    onNavItemClicked(it)
                }
            )
        }
    }
}