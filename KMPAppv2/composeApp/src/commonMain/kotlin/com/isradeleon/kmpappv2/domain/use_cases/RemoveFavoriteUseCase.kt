package com.isradeleon.kmpappv2.domain.use_cases

import com.isradeleon.kmpappv2.domain.repository.PortfolioRepository

class RemoveFavoriteUseCase(
    private val portfolioRepository: PortfolioRepository
) {
    suspend fun execute(id: String) {
        portfolioRepository.removeFavoriteCoin(id)
    }
}