package com.isradeleon.kmpappv2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserBalanceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val cashBalance: Double
)
