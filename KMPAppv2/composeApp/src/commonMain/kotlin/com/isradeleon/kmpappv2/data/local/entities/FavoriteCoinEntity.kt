package com.isradeleon.kmpappv2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCoinEntity(
    @PrimaryKey
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val color: String,
    val iconUrl: String,
    val price: Double,
    val change: Double
)
