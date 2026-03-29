package com.isradeleon.kmpappv2.domain.use_cases

import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.domain.repository.PortfolioRepository

class AddToFavoritesUseCase(
    private val repository: PortfolioRepository
) {
    suspend fun execute(coin: Coin) {
        repository.saveFavoriteCoin(coin)
    }
}