package com.isradeleon.kmpappv2.domain.use_cases

import com.isradeleon.kmpappv2.common.Outcome
import com.isradeleon.kmpappv2.domain.repository.CoinsRepository
import com.isradeleon.kmpappv2.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.first

class UpdateLocalCoinsUseCase(
    private val portfolioRepository: PortfolioRepository,
    private val coinsRepository: CoinsRepository
) {
    suspend fun execute() {
        val coinsData = coinsRepository.getCoins()
        if (coinsData is Outcome.Success) {
            val favorites = portfolioRepository.observeFavoriteCoins().first()
            val coins = coinsData.data.filter { coin ->
                favorites.find { it.id == coin.id } != null
            }
            if (coins.isNotEmpty())
                portfolioRepository.saveFavoriteCoinsList(coins)
        }
    }
}