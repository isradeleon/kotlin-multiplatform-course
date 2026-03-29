package com.isradeleon.kmpappv2.domain.repository

import com.isradeleon.kmpappv2.common.FailureDetail
import com.isradeleon.kmpappv2.common.Outcome
import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.domain.model.PriceHistoryItem

interface CoinsRepository {
    suspend fun getCoins(): Outcome<List<Coin>, FailureDetail>
    suspend fun getPriceHistory(id: String): Outcome<List<PriceHistoryItem>, FailureDetail>
    suspend fun getCoinDetails(id: String): Outcome<Coin, FailureDetail>
}