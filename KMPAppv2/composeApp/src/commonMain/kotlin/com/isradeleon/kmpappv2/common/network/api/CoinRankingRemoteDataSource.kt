package com.isradeleon.kmpappv2.common.network.api

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Outcome
import com.isradeleon.kmpappv2.common.network.safeCall
import com.isradeleon.kmpappv2.data.remote.sources.CoinsRemoteDataSource
import com.isradeleon.kmpappv2.data.remote.dtos.CoinDetailResponseDto
import com.isradeleon.kmpappv2.data.remote.dtos.CoinPriceHistoryResponseDto
import com.isradeleon.kmpappv2.data.remote.dtos.CoinsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://api.coinranking.com/v2"

class CoinRankingRemoteDataSource(
    private val client: HttpClient
): CoinsRemoteDataSource {
    override suspend fun getCoins(): Outcome<CoinsResponseDto, FailureDetail.Remote> {
        return safeCall {
            client.get("$BASE_URL/coins")
        }
    }

    override suspend fun getPriceHistory(id: String): Outcome<CoinPriceHistoryResponseDto, FailureDetail.Remote> {
        return safeCall {
            client.get("$BASE_URL/coin/$id/history")
        }
    }

    override suspend fun getCoinDetails(id: String): Outcome<CoinDetailResponseDto, FailureDetail.Remote> {
        return safeCall {
            client.get("$BASE_URL/coin/$id")
        }
    }
}