package com.isradeleon.kmpappv2.domain.use_cases

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Outcome
import com.isradeleon.kmpappv2.common.map
import com.isradeleon.kmpappv2.data.mapper.toCoin
import com.isradeleon.kmpappv2.data.remote.sources.CoinsRemoteDataSource
import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.domain.repository.CoinsRepository

class GetCoinsUseCase(
    private val repository: CoinsRepository
) {
    suspend fun execute(): Outcome<List<Coin>, FailureDetail> {
        return repository.getCoins()
    }
}