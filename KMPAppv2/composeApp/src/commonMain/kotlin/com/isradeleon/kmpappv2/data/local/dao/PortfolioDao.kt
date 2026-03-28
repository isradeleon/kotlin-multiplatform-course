package com.isradeleon.kmpappv2.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.isradeleon.kmpappv2.data.local.entities.PortfolioCoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao {
    @Upsert
    suspend fun insert(portfolioCoinEntity: PortfolioCoinEntity)

    @Query("SELECT * FROM PortfolioCoinEntity ORDER BY timestamp DESC")
    fun getAllOwnedCoins(): Flow<List<PortfolioCoinEntity>>

    @Query("SELECT * FROM PortfolioCoinEntity WHERE id = :id")
    suspend fun getCoinById(id: String): PortfolioCoinEntity?

    @Query("DELETE FROM PortfolioCoinEntity WHERE id = :id")
    suspend fun deleteCoinById(id: String)
}