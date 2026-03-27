package com.isradeleon.kmpappv2.presentation.coins

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.presentation.state.CoinsState

@Composable
fun CoinsListScreen(
    onCoinClicked: (String) -> Unit
) {
    val coinsListViewModel = viewModel(CoinsListViewModel::class)
    val state by coinsListViewModel.state.collectAsStateWithLifecycle() // Lifecycle aware!

    CoinsListContent(
        state = state,
        onCoinClicked = onCoinClicked
    )
}

@Composable
fun CoinsListContent(
    state: CoinsState,
    onCoinClicked: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CoinsList(
            coins = state.coins,
            onCoinClicked = onCoinClicked
        )
    }
}

@Composable
fun CoinsList(
    coins: List<Coin>,
    onCoinClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Header
        item {
            Text(
                text = "Top Coins",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                modifier = Modifier.padding(16.dp)
            )
        }

        items(
            items = coins,
            key = { it.id }
        ) {
            CoinListItem(
                coin = it,
                onCoinClicked = onCoinClicked
            )
        }
    }
}

@Composable
fun CoinListItem(
    coin: Coin,
    onCoinClicked: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .clickable { onCoinClicked(coin.id) }
            .padding(16.dp)
    ) {

    }
}