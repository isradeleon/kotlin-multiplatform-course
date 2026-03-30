package com.isradeleon.kmpappv2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isradeleon.kmpappv2.presentation.navigation.Screen
import com.isradeleon.kmpappv2.presentation.navigation.toNavigationIcon
import com.isradeleon.kmpappv2.presentation.screens.coin_details_screen.CoinDetailsScreen
import com.isradeleon.kmpappv2.presentation.screens.coins_list_screen.CoinsListScreen
import com.isradeleon.kmpappv2.presentation.screens.favorite_coins_screen.FavoriteCoinsScreen
import com.isradeleon.kmpappv2.theme.KMPAppV2Theme
import kmpappv2.composeapp.generated.resources.Res
import kmpappv2.composeapp.generated.resources.favorite_24dp
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
fun App() {
    /**
     * Compose Nav Controller
     */
    val favoritesNavController = rememberNavController()
    val marketNavController = rememberNavController()

    var selectedItem by rememberSaveable { mutableStateOf(0) }
    val bottomTabs = listOf(
        Screen.FavoriteCoins,
        Screen.CoinsList
    )

    KMPAppV2Theme {
        Scaffold(
            topBar = {

            },
            bottomBar = {
                NavigationBar {
                    bottomTabs.forEach {
                        NavigationBarItem(
                            label = {
                                Text(it.title)
                            },
                            icon = {
                                Image(
                                    painterResource(it.toNavigationIcon()),
                                    contentDescription = "Favorites Tab"
                                )
                            },
                            selected = false,
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
                            onCoinClicked = {}
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
                            onCoinClicked = {}
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