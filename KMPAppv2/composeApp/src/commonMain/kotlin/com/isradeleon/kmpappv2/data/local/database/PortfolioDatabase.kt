package com.isradeleon.kmpappv2.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.isradeleon.kmpappv2.data.local.dao.FavoriteDao
import com.isradeleon.kmpappv2.data.local.entities.FavoriteCoinEntity

@Database(
    entities = [
        FavoriteCoinEntity::class
    ],
    version = 4
)
@ConstructedBy(PortfolioDatabaseConstructor::class)
abstract class PortfolioDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "portfolio.db"
    }

    /**
     * DAOs
     */
    abstract fun favoriteDao(): FavoriteDao
}