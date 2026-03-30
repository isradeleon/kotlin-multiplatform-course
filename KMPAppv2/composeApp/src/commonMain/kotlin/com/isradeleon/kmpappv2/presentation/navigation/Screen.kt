package com.isradeleon.kmpappv2.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(
    val title: String
) {
    @Serializable
    object Home: Screen(
        title = "Home"
    )

    @Serializable
    object CoinsListTab: Screen(
        title = "Market"
    )

    @Serializable
    object FavoriteCoinsTab: Screen(
        title = "Favorites"
    )

    @Serializable
    data class CoinDetails(
        val coinId: String
    ): Screen(
        title = "Coin Details",
    )

    companion object {
        /**
         * IMPORTANT WORKAROUND WARNING:
         *
         * Compose navigation currently does not support casting
         * NavDestination to an object (toRoute method),
         * without knowing the target type.
         *
         * Since Kotlin reflection isn't well-supported in KMP,
         * we had to do it this way:
         */
        fun fromBackStackEntry(
            entry: NavBackStackEntry
        ): Screen? {
            return fromBackStackEntryTryCatch<CoinsListTab>(entry)
                ?: fromBackStackEntryTryCatch<FavoriteCoinsTab>(entry)
                ?: fromBackStackEntryTryCatch<CoinDetails>(entry)
        }

    }
}

fun Screen.toNavigationIcon(): ImageVector {
    return when (this) {
        Screen.Home -> Icons.Outlined.Favorite
        Screen.FavoriteCoinsTab -> Icons.Outlined.Favorite
        Screen.CoinsListTab -> Icons.Outlined.QueryStats
        is Screen.CoinDetails -> Icons.Outlined.Favorite
    }
}

private inline fun <reified T> fromBackStackEntryTryCatch(
    entry: NavBackStackEntry
): T? {
    return try {
        entry.toRoute<T>()
    } catch (_: Throwable) {
        //exception.printStackTrace()
        null
    }
}