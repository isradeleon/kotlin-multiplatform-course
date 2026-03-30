package com.isradeleon.kmpappv2.presentation.navigation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kmpappv2.composeapp.generated.resources.Res
import kmpappv2.composeapp.generated.resources.arrow_back_24dp
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(navController: NavController) {
    // Observe the current back stack entry to dynamically change the top bar
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Determine if we should show the back arrow (e.g., not on the 'home' screen)
    val showBackButton = currentRoute != "home" && currentRoute != null

    TopAppBar(
        title = {
            Text(text = currentRoute ?: "Home")
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = {
                    // Navigate up the back stack
                    navController.popBackStack()
                }) {
                    Icon(
                        painterResource(Res.drawable.arrow_back_24dp),
                        contentDescription = "Back action"
                    )
                }
            }
        }
    )
}