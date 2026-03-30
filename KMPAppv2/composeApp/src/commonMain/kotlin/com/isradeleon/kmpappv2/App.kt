package com.isradeleon.kmpappv2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isradeleon.kmpappv2.presentation.navigation.Navigator
import com.isradeleon.kmpappv2.presentation.navigation.Screen
import com.isradeleon.kmpappv2.presentation.navigation.components.MyBottomNavbar
import com.isradeleon.kmpappv2.presentation.navigation.components.MyTopAppBar
import com.isradeleon.kmpappv2.presentation.screens.coin_details_screen.CoinDetailsScreen
import com.isradeleon.kmpappv2.presentation.screens.coins_list_screen.CoinsListScreen
import com.isradeleon.kmpappv2.presentation.screens.favorite_coins_screen.FavoriteCoinsScreen
import com.isradeleon.kmpappv2.theme.KMPAppV2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val navigator = Navigator()

    val currentTab = navigator.currentTab.collectAsState()
    val currentScreen = navigator.currentScreen.collectAsState()
    val isHome = navigator.isHome.collectAsState()

    KMPAppV2Theme {
        Scaffold(
            topBar = {
                MyTopAppBar(
                    isHome = isHome.value,
                    onBackClick = {
                        navigator.navigateBack()
                    }
                )
            },
            bottomBar = {
                MyBottomNavbar(
                    navItems = Navigator.NAVIGATION_TABS,
                    currentNavItem = currentTab.value,
                    onNavItemClicked = {
                        navigator.navigateToTab(it)
                    }
                )
            }
        ) { padding ->
            AppContent(
                modifier = Modifier.padding(padding),
                navigator = navigator
            )
        }
    }
}

@Composable
private fun AppContent(
    modifier: Modifier,
    navigator: Navigator
) {
    val parentNavController = rememberNavController()
    navigator.setParentNavController(parentNavController)

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = parentNavController,
        //enterTransition = { fadeIn(animationSpec = tween(200)) },
        //exitTransition = { fadeOut(animationSpec = tween(200)) },
        startDestination = Screen.FavoriteCoinsTab,
    ) {
        composable<Screen.FavoriteCoinsTab> {
            val mainNestedNavController = rememberNavController()
            navigator.setNestedNavController(mainNestedNavController)

            NavHost(
                navController = mainNestedNavController,
                startDestination = Screen.FavoriteCoinsTab
            ) {
                composable<Screen.FavoriteCoinsTab> {
                    FavoriteCoinsScreen(
                        onCoinClicked = {
                            navigator.navigate(Screen.CoinDetails)
                        },
                        onExploreCoinsClicked = {
                            navigator.navigateToTab(Screen.CoinsListTab)
                        }
                    )
                }
                composable<Screen.CoinDetails> { backStackEntry ->
                    CoinDetailsScreen()
                }
            }
        }

        composable<Screen.CoinsListTab> {
            val marketNestedNavController = rememberNavController()
            navigator.setNestedNavController(marketNestedNavController)

            NavHost(
                navController = marketNestedNavController,
                startDestination = Screen.CoinsListTab
            ) {
                composable<Screen.CoinsListTab> {
                    CoinsListScreen(
                        onCoinClicked = {
                            navigator.navigate(Screen.CoinDetails)
                        }
                    )
                }
                composable<Screen.CoinDetails> { backStackEntry ->
                    CoinDetailsScreen()
                }
            }
        }
    }
}
