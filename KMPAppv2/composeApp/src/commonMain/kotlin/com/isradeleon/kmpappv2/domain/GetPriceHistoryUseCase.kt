package com.isradeleon.kmpappv2.domain

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Response
import com.isradeleon.kmpappv2.common.map
import com.isradeleon.kmpappv2.data.mapper.toPriceHistoryItem
import com.isradeleon.kmpappv2.data.remote.src.CoinsRemoteDataSource
import com.isradeleon.kmpappv2.domain.model.PriceHistoryItem

class GetPriceHistoryUseCase(
    private val source: CoinsRemoteDataSource
) {
    suspend fun execute(id: String): Response<List<PriceHistoryItem>, FailureDetail.Remote> {
        return source.getPriceHistory(id).map { response ->
            response.data.history.map {
                it.toPriceHistoryItem()
            }
        }
    }
}