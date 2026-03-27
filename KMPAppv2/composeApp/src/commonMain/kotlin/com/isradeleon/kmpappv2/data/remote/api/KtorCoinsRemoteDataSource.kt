package com.isradeleon.kmpappv2.data.remote.api

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Response
import com.isradeleon.kmpappv2.common.network.safeCall
import com.isradeleon.kmpappv2.data.remote.src.CoinsRemoteDataSource
import com.isradeleon.kmpappv2.data.remote.dto.CoinDetailResponseDto
import com.isradeleon.kmpappv2.data.remote.dto.CoinPriceHistoryResponseDto
import com.isradeleon.kmpappv2.data.remote.dto.CoinsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://api.coinranking.com/v2"

class KtorCoinsRemoteDataSource(
    private val client: HttpClient
): CoinsRemoteDataSource {
    override suspend fun getCoins(): Response<CoinsResponseDto, FailureDetail.Remote> {
        return safeCall {
            client.get("$BASE_URL/coins")
        }
    }

    override suspend fun getPriceHistory(id: String): Response<CoinPriceHistoryResponseDto, FailureDetail.Remote> {
        return safeCall {
            client.get("$BASE_URL/coin/$id/history")
        }
    }

    override suspend fun getCoinDetails(id: String): Response<CoinDetailResponseDto, FailureDetail.Remote> {
        return safeCall {
            client.get("$BASE_URL/coin/$id")
        }
    }
}