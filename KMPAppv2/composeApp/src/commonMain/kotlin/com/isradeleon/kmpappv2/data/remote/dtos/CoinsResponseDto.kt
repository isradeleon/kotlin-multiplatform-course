package com.isradeleon.kmpappv2.data.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponseDto(
    val data: CoinsListDto
)

@Serializable
data class CoinsListDto(
    val coins: List<CoinDto>
)

@Serializable
data class CoinDto(
    val uuid: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val description: String? = null,
    val color: String,
    val iconUrl: String,
    /**
     * Consider using BigDecimal in real world apps.
     * For this project using Double instead.
     */
    val price: Double,
    val change: Double
)