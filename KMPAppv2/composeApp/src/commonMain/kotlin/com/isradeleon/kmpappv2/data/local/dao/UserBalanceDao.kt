package com.isradeleon.kmpappv2.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.isradeleon.kmpappv2.data.local.entities.UserBalanceEntity

@Dao
interface UserBalanceDao {
    @Query("SELECT cashBalance FROM UserBalanceEntity LIMIT 1")
    suspend fun getCashBalance(): Double?

    @Upsert
    suspend fun insert(userBalanceEntity: UserBalanceEntity)

    @Query("UPDATE UserBalanceEntity SET cashBalance = :newBalance WHERE id = 1")
    suspend fun updateCashBalance(newBalance: Double)
}