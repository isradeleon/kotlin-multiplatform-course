package com.isradeleon.kmpappv2.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.isradeleon.kmpappv2.data.local.dao.PortfolioDao
import com.isradeleon.kmpappv2.data.local.dao.UserBalanceDao
import com.isradeleon.kmpappv2.data.local.entities.PortfolioCoinEntity
import com.isradeleon.kmpappv2.data.local.entities.UserBalanceEntity

@Database(
    entities = [
        PortfolioCoinEntity::class,
        UserBalanceEntity::class
    ],
    version = 2
)
@ConstructedBy(PortfolioDatabaseConstructor::class)
abstract class PortfolioDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "portfolio.db"
    }

    /**
     * DAOs
     */
    abstract fun portfolioDao(): PortfolioDao
    abstract fun userBalanceDao(): UserBalanceDao
}