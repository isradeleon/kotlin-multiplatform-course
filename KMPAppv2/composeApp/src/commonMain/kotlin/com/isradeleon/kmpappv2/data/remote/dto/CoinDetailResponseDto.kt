package com.isradeleon.kmpappv2.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailResponseDto(
    val data: CoinDetailDto
)

@Serializable
data class CoinDetailDto(
    val coin: CoinDto
)