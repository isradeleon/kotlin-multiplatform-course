package com.isradeleon.kmpappv2.presentation.screens.coin_details_screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.isradeleon.kmpappv2.common.utils.formatFiatCurrency
import com.isradeleon.kmpappv2.common.utils.formatPercentage
import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.domain.model.PriceHistoryItem
import com.isradeleon.kmpappv2.presentation.extensions.isPositiveChange
import com.isradeleon.kmpappv2.theme.LocalCoinRoutineColorsPalette
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CoinDetailsScreen(
    coinId: String
) {
    val coinDetailsViewModel = koinViewModel<CoinDetailsViewModel> {
        parametersOf(coinId)
    }
    val state by coinDetailsViewModel.state.collectAsStateWithLifecycle()

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
    else if (state.coin != null)
        CoinDetailsContent(
            coin = state.coin!!,
            priceHistory = state.priceHistory
        )
}

@Composable
private fun CoinDetailsContent(
    coin: Coin,
    priceHistory: List<PriceHistoryItem>
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = coin.iconUrl,
                    contentDescription = "${coin.name} icon",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(4.dp)
                        .clip(CircleShape)
                        .size(80.dp)
                )

                Spacer(Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = formatFiatCurrency(coin.price),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = coin.name,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = formatPercentage(coin.change),
                        color = if (coin.isPositiveChange)
                            LocalCoinRoutineColorsPalette.current.profitGreen
                        else
                            LocalCoinRoutineColorsPalette.current.lossRed,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = coin.symbol,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                }
            }

            Spacer(Modifier.width(32.dp))

            PerformanceChart(
                modifier = Modifier.height(200.dp),
                nodes = priceHistory.map { it.price }
            )
        }
    }
}