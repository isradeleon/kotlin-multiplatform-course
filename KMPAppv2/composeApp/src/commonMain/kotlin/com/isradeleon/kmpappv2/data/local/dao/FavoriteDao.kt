package com.isradeleon.kmpappv2.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.isradeleon.kmpappv2.data.local.entities.FavoriteCoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Upsert
    suspend fun upsert(favoriteCoinEntity: FavoriteCoinEntity)

    @Query("SELECT * FROM FavoriteCoinEntity ORDER BY timestamp DESC")
    fun getAllFavoriteCoins(): Flow<List<FavoriteCoinEntity>>

    @Query("SELECT * FROM FavoriteCoinEntity WHERE id = :id")
    suspend fun getFavoriteCoinById(id: String): FavoriteCoinEntity?

    @Query("DELETE FROM FavoriteCoinEntity WHERE id = :id")
    suspend fun deleteFavoriteCoinById(id: String)
}