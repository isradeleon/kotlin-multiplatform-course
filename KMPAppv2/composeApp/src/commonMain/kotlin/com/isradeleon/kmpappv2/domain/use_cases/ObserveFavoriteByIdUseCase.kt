package com.isradeleon.kmpappv2.domain.use_cases

import com.isradeleon.kmpappv2.domain.model.Coin
import com.isradeleon.kmpappv2.domain.repository.PortfolioRepository
import kotlinx.coroutines.flow.Flow

class ObserveFavoriteByIdUseCase(
    private val repository: PortfolioRepository
) {
    fun execute(id: String): Flow<Coin?> {
        return repository.observeFavoriteById(id)
    }
}