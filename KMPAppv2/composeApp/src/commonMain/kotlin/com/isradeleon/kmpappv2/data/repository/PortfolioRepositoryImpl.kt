package com.isradeleon.kmpappv2.data.repository

import com.isradeleon.kmpappv2.data.local.dao.FavoriteDao
import com.isradeleon.kmpappv2.data.mapper.toCoin
import com.isradeleon.kmpappv2.data.mapper.toFavoriteCoinEntity
import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PortfolioRepositoryImpl(
    private val favoriteDao: FavoriteDao
): PortfolioRepository {
    override fun observeFavoriteCoins(): Flow<List<Coin>> {
        return favoriteDao.getAllFavoriteCoins().map {
            it.map { entity ->
                entity.toCoin()
            }
        }
    }

    override fun observeFavoriteById(id: String): Flow<Coin?> {
        return favoriteDao.getFavoriteCoinById(id).map {
            it?.toCoin()
        }
    }

    override suspend fun saveFavoriteCoin(coin: Coin) {
        favoriteDao.upsert(
            coin.toFavoriteCoinEntity()
        )
    }

    override suspend fun removeFavoriteCoin(id: String) {
        favoriteDao.deleteFavoriteCoinById(id)
    }
}