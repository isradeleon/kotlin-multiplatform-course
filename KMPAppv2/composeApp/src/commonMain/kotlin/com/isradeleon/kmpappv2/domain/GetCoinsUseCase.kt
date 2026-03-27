package com.isradeleon.kmpappv2.domain

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Response
import com.isradeleon.kmpappv2.common.map
import com.isradeleon.kmpappv2.data.mapper.toCoin
import com.isradeleon.kmpappv2.data.remote.src.CoinsRemoteDataSource
import com.isradeleon.kmpappv2.domain.model.Coin

class GetCoinsUseCase(
    private val source: CoinsRemoteDataSource
) {
    suspend fun execute(): Response<List<Coin>, FailureDetail.Remote> {
        return source.getCoins().map { response ->
            response.data.coins.map {
                it.toCoin()
            }
        }
    }
}