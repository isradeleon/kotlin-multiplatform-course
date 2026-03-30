package com.isradeleon.kmpappv2.presentation.screens.coins_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.isradeleon.kmpappv2.common.utils.formatFiatCurrency
import com.isradeleon.kmpappv2.common.utils.formatPercentage
import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.presentation.extensions.isPositiveChange
import com.isradeleon.kmpappv2.theme.LocalCoinRoutineColorsPalette
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CoinsListScreen(
    onCoinClicked: (String) -> Unit
) {
    val coinsListViewModel = koinViewModel<CoinsListViewModel>()
    val state by coinsListViewModel.state.collectAsStateWithLifecycle() // Lifecycle aware!

    if (state.isLoading)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    else if (state.error != null)
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(state.error!!),
                modifier = Modifier.padding(32.dp).fillMaxWidth(),
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        }
    else
        CoinsListContent(
            state = state,
            onCoinClicked = onCoinClicked
        )
}

@Composable
private fun CoinsListContent(
    state: CoinsListState,
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
private fun CoinsList(
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
                text = "Crypto Market",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                modifier = Modifier.padding(16.dp)
            )
        }

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
        AsyncImage(
            model = coin.iconUrl,
            contentDescription = "${coin.name} icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(4.dp)
                .clip(CircleShape)
                .size(40.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = coin.name,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = coin.symbol,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = formatFiatCurrency(coin.price),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = formatPercentage(coin.change),
                color = if (coin.isPositiveChange)
                        LocalCoinRoutineColorsPalette.current.profitGreen
                    else
                        LocalCoinRoutineColorsPalette.current.lossRed,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        }
    }
}