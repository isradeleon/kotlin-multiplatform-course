package com.isradeleon.kmpappv2.presentation.screens.favorite_coins_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.presentation.screens.coins_list_screen.CoinListItem
import com.isradeleon.kmpappv2.theme.LocalCoinRoutineColorsPalette
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteCoinsScreen(
    onCoinClicked: (String) -> Unit,
    onExploreCoinsClicked: () -> Unit
) {
    val favoriteCoinsViewModel = koinViewModel<FavoriteCoinsViewModel>()
    val coins by favoriteCoinsViewModel.observeFavoriteCoins()
        .collectAsStateWithLifecycle(initialValue = emptyList())

    if (coins.isNotEmpty())
        FavoriteCoinsContent(
            coins = coins,
            onCoinClicked = onCoinClicked,
            onDismissCoin = { id ->
                favoriteCoinsViewModel.removeFavoriteCoin(id)
            }
        )
    else
        EmptyFavoriteCoinsContent(
            onExploreCoinsClicked = onExploreCoinsClicked
        )
}

@Composable
private fun EmptyFavoriteCoinsContent(
    onExploreCoinsClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "No favorite coins found",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onExploreCoinsClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = LocalCoinRoutineColorsPalette.current.profitGreen
                )
            ) {
                Text(
                    text = "Explore coins",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
private fun FavoriteCoinsContent(
    coins: List<Coin>,
    onCoinClicked: (String) -> Unit,
    onDismissCoin: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FavoriteCoinsList(
            coins = coins,
            onCoinClicked = onCoinClicked,
            onDismissCoin = onDismissCoin
        )
    }
}

@Composable
private fun FavoriteCoinsList(
    coins: List<Coin>,
    onCoinClicked: (String) -> Unit,
    onDismissCoin: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Coins list
        items(
            items = coins,
            key = { it.id }
        ) {
            CoinListItem(
                coin = it,
                onCoinClicked = onCoinClicked
            )

            HorizontalDivider()
        }
    }
}

@Composable
private fun FavoriteCoinItem(
    coin: Coin,
    onCoinClicked: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState()
    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(Color.Red),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    "Delete"
                )
            }
        },
        onDismiss = { onDismiss() }
    ) {
        CoinListItem(
            coin = coin,
            onCoinClicked = onCoinClicked
        )
    }
}