package com.isradeleon.kmpappv2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.isradeleon.kmpappv2.presentation.navigation.Navigator
import com.isradeleon.kmpappv2.presentation.navigation.Screen
import com.isradeleon.kmpappv2.presentation.navigation.components.MyBottomNavbar
import com.isradeleon.kmpappv2.presentation.navigation.components.MyTopAppBar
import com.isradeleon.kmpappv2.presentation.screens.coin_details_screen.CoinDetailsScreen
import com.isradeleon.kmpappv2.presentation.screens.coins_list_screen.CoinsListScreen
import com.isradeleon.kmpappv2.presentation.screens.favorite_coins_screen.FavoriteCoinsScreen
import com.isradeleon.kmpappv2.theme.KMPAppV2Theme
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()
    val isHome = backStack?.destination?.hasRoute<Screen.Home>() ?: false
    val selectedBottomTab = remember { mutableStateOf(0) }

    KMPAppV2Theme {
        Scaffold(
            topBar = {
                MyTopAppBar(
                    isHome = isHome,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        ) { padding ->
            NavHost(
                modifier = Modifier.padding(
                    top = padding.calculateTopPadding()
                ),
                navController = navController,
                startDestination = Screen.Home,
            ) {
                composable<Screen.Home> {
                    HomeScreen(
                        selectedBottomTab = selectedBottomTab,
                        onCoinClicked = {
                            navController.navigate(Screen.CoinDetails)
                        },
                    )
                }
                composable<Screen.CoinDetails> { backStackEntry ->
                    CoinDetailsScreen()
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(
    onCoinClicked: (String) -> Unit,
    selectedBottomTab: MutableState<Int>,
) {
    val scope = rememberCoroutineScope()
    val bottomTabs = listOf(Screen.FavoriteCoinsTab, Screen.CoinsListTab)
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 2 }
    )

    Scaffold(
        bottomBar = {
            MyBottomNavbar(
                navItems = bottomTabs,
                currentNavItem = bottomTabs[selectedBottomTab.value],
                onNavItemClicked = {
                    selectedBottomTab.value = bottomTabs.indexOf(it)
                    scope.launch {
                        pagerState.animateScrollToPage(
                            bottomTabs.indexOf(it)
                        )
                    }
                }
            )
        }
    ) { padding ->
        HorizontalPager(
            modifier = Modifier.fillMaxSize()
                .padding(bottom = padding.calculateBottomPadding()),
            state = pagerState
        ) { page ->
            when (page) {
                0 -> FavoriteCoinsScreen(
                    onCoinClicked = onCoinClicked,
                    onExploreCoinsClicked = {
                        selectedBottomTab.value = 1
                        scope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    }
                )
                1 -> CoinsListScreen(
                    onCoinClicked = onCoinClicked
                )
                else -> {}
            }
        }
    }
}

/**
 * Previous impl with the Navigator class.
 * Keep for reference & future possible implementations.
 */
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
