package com.isradeleon.kmpappv2.presentation.screens.favorite_coins_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteCoinsScreen(
    modifier: Modifier
) {
    val favoriteCoinsViewModel = koinViewModel<FavoriteCoinsViewModel>()
    val coins by favoriteCoinsViewModel.observeFavoriteCoins()
        .collectAsStateWithLifecycle(initialValue = emptyList())

    if (coins.isNotEmpty())
        FavoriteCoinsContent(
            modifier = modifier,
            coins = coins,
            onCoinClicked = {

            },
            onDismissCoin = { id ->
                favoriteCoinsViewModel.removeFavoriteCoin(id)
            }
        )
    else
        EmptyFavoriteCoinsContent(
            modifier = modifier,
            onExploreCoinsClicked = {

            }
        )
}

@Composable
private fun EmptyFavoriteCoinsContent(
    modifier: Modifier,
    onExploreCoinsClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text("No favorite coins")
            Button(
                onClick = onExploreCoinsClicked
            ) {
                Text("Explore coins")
            }
        }
    }
}

@Composable
private fun FavoriteCoinsContent(
    modifier: Modifier,
    coins: List<Coin>,
    onCoinClicked: (String) -> Unit,
    onDismissCoin: (String) -> Unit
) {
    Box(
        modifier = modifier
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
            FavoriteCoinItem(
                coin = it,
                onCoinClicked = onCoinClicked,
                onDismiss = {
                    onDismissCoin(it.id)
                }
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