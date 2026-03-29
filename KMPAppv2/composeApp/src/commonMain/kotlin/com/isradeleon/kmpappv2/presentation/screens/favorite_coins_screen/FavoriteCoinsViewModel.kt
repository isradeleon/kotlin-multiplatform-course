package com.isradeleon.kmpappv2.presentation.screens.favorite_coins_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isradeleon.kmpappv2.domain.use_cases.ObserveFavoritesUseCase
import com.isradeleon.kmpappv2.domain.use_cases.RemoveFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class FavoriteCoinsViewModel(
    private val observeFavoritesUseCase: ObserveFavoritesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
): ViewModel() {
    fun observeFavoriteCoins() = observeFavoritesUseCase.execute()

    fun removeFavoriteCoin(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFavoriteUseCase.execute(id)
        }
    }
}