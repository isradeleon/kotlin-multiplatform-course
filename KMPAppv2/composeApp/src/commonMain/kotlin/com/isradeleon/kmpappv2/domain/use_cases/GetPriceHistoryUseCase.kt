package com.isradeleon.kmpappv2.domain.use_cases

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Outcome
import com.isradeleon.kmpappv2.domain.model.PriceHistoryItem
import com.isradeleon.kmpappv2.domain.repository.CoinsRepository

class GetPriceHistoryUseCase(
    private val repository: CoinsRepository
) {
    suspend fun execute(id: String): Outcome<List<PriceHistoryItem>, FailureDetail> {
        return repository.getPriceHistory(id)
    }
}