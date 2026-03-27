package com.isradeleon.kmpappv2.data.remote.src

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Response
import com.isradeleon.kmpappv2.data.remote.dto.CoinDetailResponseDto
import com.isradeleon.kmpappv2.data.remote.dto.CoinPriceHistoryResponseDto
import com.isradeleon.kmpappv2.data.remote.dto.CoinsResponseDto

interface CoinsRemoteDataSource {
    suspend fun getCoins():
            Response<CoinsResponseDto, FailureDetail.Remote>
    suspend fun getPriceHistory(id: String):
            Response<CoinPriceHistoryResponseDto, FailureDetail.Remote>
    suspend fun getCoinDetails(id: String):
            Response<CoinDetailResponseDto, FailureDetail.Remote>
}