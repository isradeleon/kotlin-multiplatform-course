package com.isradeleon.kmpappv2.domain.use_cases

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Response
import com.isradeleon.kmpappv2.common.map
import com.isradeleon.kmpappv2.data.mapper.toCoin
import com.isradeleon.kmpappv2.data.remote.src.CoinsRemoteDataSource
import com.isradeleon.kmpappv2.domain.model.Coin

class GetCoinDetailUseCase(
    private val source: CoinsRemoteDataSource
) {
    suspend fun execute(id: String): Response<Coin, FailureDetail.Remote> {
        return source.getCoinDetails(id).map { response ->
            response.data.coin.toCoin()
        }
    }
}