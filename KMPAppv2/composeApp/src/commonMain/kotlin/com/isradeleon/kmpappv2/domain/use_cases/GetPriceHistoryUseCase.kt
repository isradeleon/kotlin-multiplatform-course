package com.isradeleon.kmpappv2.domain.use_cases

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Outcome
import com.isradeleon.kmpappv2.common.map
import com.isradeleon.kmpappv2.data.mapper.toPriceHistoryItem
import com.isradeleon.kmpappv2.data.remote.sources.CoinsRemoteDataSource
import com.isradeleon.kmpappv2.domain.model.PriceHistoryItem

class GetPriceHistoryUseCase(
    private val source: CoinsRemoteDataSource
) {
    suspend fun execute(id: String): Outcome<List<PriceHistoryItem>, FailureDetail.Remote> {
        return source.getPriceHistory(id).map { response ->
            response.data.history.map {
                it.toPriceHistoryItem()
            }
        }
    }
}