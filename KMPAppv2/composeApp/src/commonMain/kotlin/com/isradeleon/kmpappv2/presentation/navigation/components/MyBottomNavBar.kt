package com.isradeleon.kmpappv2.presentation.navigation.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.isradeleon.kmpappv2.presentation.navigation.Screen

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
                    NavIcon(it)
                },
                selected = it == currentNavItem,
                onClick = {
                    onNavItemClicked(it)
                }
            )
        }
    }
}