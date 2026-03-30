package com.isradeleon.kmpappv2.presentation.screens.coin_details_screen

import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.domain.model.PriceHistoryItem
import org.jetbrains.compose.resources.StringResource

data class CoinDetailsState(
    val isLoading: Boolean = true,
    val error: StringResource? = null,
    val coin: Coin? = null,
    val priceHistory: List<PriceHistoryItem> = emptyList()
)