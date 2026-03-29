package com.isradeleon.kmpappv2.presentation.screens.favorite_coins_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteCoinsScreen() {
    val favoriteCoinsViewModel = koinViewModel<FavoriteCoinsViewModel>()
    val coins by favoriteCoinsViewModel.observeFavoriteCoins().collectAsStateWithLifecycle(
        initialValue = emptyList()
    )
}