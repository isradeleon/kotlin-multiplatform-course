package com.isradeleon.kmpappv2.presentation.screens.favorite_coins_screen

import androidx.lifecycle.ViewModel
import com.isradeleon.kmpappv2.domain.use_cases.ObserveFavoritesUseCase

class FavoriteCoinsViewModel(
    private val observeFavoritesUseCase: ObserveFavoritesUseCase
): ViewModel() {
    fun observeFavoriteCoins() = observeFavoritesUseCase.execute()
}