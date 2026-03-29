package com.isradeleon.kmpappv2.data.repository

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Outcome
import com.isradeleon.kmpappv2.common.map
import com.isradeleon.kmpappv2.data.mapper.toCoin
import com.isradeleon.kmpappv2.data.mapper.toPriceHistoryItem
import com.isradeleon.kmpappv2.data.remote.sources.CoinsRemoteDataSource
import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.domain.model.PriceHistoryItem
import com.isradeleon.kmpappv2.domain.repository.CoinsRepository

class CoinsRepositoryImpl(
    private val coinsRemoteDataSource: CoinsRemoteDataSource
): CoinsRepository {
    override suspend fun getCoins(): Outcome<List<Coin>, FailureDetail.Remote> {
        return coinsRemoteDataSource.getCoins().map {
            it.data.coins.map { coinDto ->
                coinDto.toCoin()
            }
        }
    }

    override suspend fun getPriceHistory(id: String): Outcome<List<PriceHistoryItem>, FailureDetail.Remote> {
        return coinsRemoteDataSource.getPriceHistory(id).map {
            it.data.history.map { priceHistoryDto ->
                priceHistoryDto.toPriceHistoryItem()
            }
        }
    }

    override suspend fun getCoinDetails(id: String): Outcome<Coin, FailureDetail.Remote> {
        return coinsRemoteDataSource.getCoinDetails(id).map {
            it.data.coin.toCoin()
        }
    }
}