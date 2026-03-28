package com.isradeleon.kmpappv2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PortfolioCoinEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val avgPurchasePrice: Double,
    val amountOwned: Double,
    val timestamp: Long
)
