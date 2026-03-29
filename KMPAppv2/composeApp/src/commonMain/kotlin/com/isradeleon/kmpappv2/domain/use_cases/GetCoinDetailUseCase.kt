package com.isradeleon.kmpappv2.domain.use_cases

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Outcome
import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.domain.repository.CoinsRepository

class GetCoinDetailUseCase(
    private val repository: CoinsRepository
) {
    suspend fun execute(id: String): Outcome<Coin, FailureDetail> {
        return repository.getCoinDetails(id)
    }
}