package com.isradeleon.kmpappv2.data.remote.sources

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Outcome
import com.isradeleon.kmpappv2.data.remote.dto.CoinDetailResponseDto
import com.isradeleon.kmpappv2.data.remote.dto.CoinPriceHistoryResponseDto
import com.isradeleon.kmpappv2.data.remote.dto.CoinsResponseDto

interface CoinsRemoteDataSource {
    suspend fun getCoins():
            Outcome<CoinsResponseDto, FailureDetail.Remote>
    suspend fun getPriceHistory(id: String):
            Outcome<CoinPriceHistoryResponseDto, FailureDetail.Remote>
    suspend fun getCoinDetails(id: String):
            Outcome<CoinDetailResponseDto, FailureDetail.Remote>
}