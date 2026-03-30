package com.isradeleon.kmpappv2.presentation.navigation

import kmpappv2.composeapp.generated.resources.Res
import kmpappv2.composeapp.generated.resources.favorite_24dp
import kmpappv2.composeapp.generated.resources.query_stats_24dp
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource

@Serializable
sealed class Screen(
    val title: String
) {
    @Serializable
    object CoinsList: Screen(
        title = "Market"
    )

    @Serializable
    object FavoriteCoins: Screen(
        title = "Favorites"
    )

    @Serializable
    object CoinDetails: Screen(
        title = "Coin Details"
    )
}

fun Screen.toNavigationIcon(): DrawableResource {
    return when (this) {
        Screen.CoinDetails -> Res.drawable.favorite_24dp
        Screen.FavoriteCoins -> Res.drawable.favorite_24dp
        Screen.CoinsList -> Res.drawable.query_stats_24dp
    }
}