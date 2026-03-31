package com.isradeleon.kmpappv2.domain.repository

import com.isradeleon.kmpappv2.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    fun observeFavoriteCoins(): Flow<List<Coin>>
    fun observeFavoriteById(id: String): Flow<Coin?>
    suspend fun saveFavoriteCoin(coin: Coin)
    suspend fun saveFavoriteCoinsList(coins: List<Coin>)
    suspend fun removeFavoriteCoin(id: String)
}