package com.isradeleon.kmpappv2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.isradeleon.kmpappv2.presentation.navigation.Screen
import com.isradeleon.kmpappv2.presentation.navigation.components.NavIcon
import com.isradeleon.kmpappv2.presentation.navigation.components.TopNavigationBar
import com.isradeleon.kmpappv2.presentation.navigation.toNavigationIcon
import com.isradeleon.kmpappv2.presentation.screens.coin_details_screen.CoinDetailsScreen
import com.isradeleon.kmpappv2.presentation.screens.coins_list_screen.CoinsListScreen
import com.isradeleon.kmpappv2.presentation.screens.favorite_coins_screen.FavoriteCoinsScreen
import com.isradeleon.kmpappv2.theme.KMPAppV2Theme
import kmpappv2.composeapp.generated.resources.Res
import kmpappv2.composeapp.generated.resources.arrow_back_24dp
import kmpappv2.composeapp.generated.resources.favorite_24dp
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    /**
     * Compose Nav Controller
     */
    val favoritesNavController = rememberNavController()
    val marketNavController = rememberNavController()

    val navBackStackEntry by favoritesNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Check if the current destination has the Home via typesafe
    val isHome = currentDestination?.hasRoute<Screen.FavoriteCoins>() == true
    var selectedItem by rememberSaveable { mutableStateOf(0) }
    val bottomTabs = listOf(
        Screen.FavoriteCoins,
        Screen.CoinsList
    )

    KMPAppV2Theme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "KMP Crypto",
                            textAlign = TextAlign.Center
                        )
                    },
                    navigationIcon = {
                        if (
                            selectedItem != 0 || !isHome
                        ) {
                            IconButton(onClick = {
                                // Navigate up the back stack
                                if (selectedItem == 0) {
                                    favoritesNavController.popBackStack()
                                } else if(!marketNavController.popBackStack()) {
                                    selectedItem = 0
                                }
                            }) {
                                Icon(
                                    painterResource(Res.drawable.arrow_back_24dp),
                                    contentDescription = "Back action"
                                )
                            }
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    bottomTabs.forEach {
                        NavigationBarItem(
                            label = {
                                Text(it.title)
                            },
                            icon = {
                                NavIcon(it)
                            },
                            selected = bottomTabs.indexOf(it) == selectedItem,
                            onClick = {
                                selectedItem = bottomTabs.indexOf(it)
                            }
                        )
                    }
                }
            }
        ) { padding ->
            if (selectedItem == 0) {
                NavHost(
                    navController = favoritesNavController,
                    startDestination = Screen.FavoriteCoins
                ) {
                    composable<Screen.FavoriteCoins> {
                        FavoriteCoinsScreen(
                            modifier = Modifier.padding(padding),
                            onExploreCoinsClicked = {},
                            onCoinClicked = {
                                favoritesNavController.navigate(Screen.CoinDetails)
                            }
                        )
                    }

                    composable<Screen.CoinDetails> {
                        CoinDetailsScreen()
                    }
                }
            } else {
                NavHost(
                    navController = marketNavController,
                    startDestination = Screen.CoinsList
                ) {
                    composable<Screen.CoinsList> {
                        CoinsListScreen(
                            modifier = Modifier.padding(padding),
                            onCoinClicked = {
                                marketNavController.navigate(Screen.CoinDetails)
                            }
                        )
                    }

                    composable<Screen.CoinDetails> {
                        CoinDetailsScreen()
                    }
                }
            }
        }
    }
}