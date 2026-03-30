package com.isradeleon.kmpappv2.presentation.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
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
    object CoinsListTab: Screen(
        title = "Market"
    )

    @Serializable
    object FavoriteCoinsTab: Screen(
        title = "Favorites"
    )

    @Serializable
    object CoinDetails: Screen(
        title = "Coin Details"
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

fun Screen.toNavigationIcon(): DrawableResource {
    return when (this) {
        Screen.CoinDetails -> Res.drawable.favorite_24dp
        Screen.FavoriteCoinsTab -> Res.drawable.favorite_24dp
        Screen.CoinsListTab -> Res.drawable.query_stats_24dp
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