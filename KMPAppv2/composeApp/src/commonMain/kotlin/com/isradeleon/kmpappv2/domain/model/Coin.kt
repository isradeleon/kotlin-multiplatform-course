package com.isradeleon.kmpappv2.domain.model

data class Coin(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val description: String?,
    val color: String,
    val iconUrl: String,
    /**
     * Consider using BigDecimal in real world apps.
     * For this project using Double instead.
     */
    val price: Double,
    val change: Double
)
