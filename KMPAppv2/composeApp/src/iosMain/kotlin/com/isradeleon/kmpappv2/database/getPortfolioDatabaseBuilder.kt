package com.isradeleon.kmpappv2.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.isradeleon.kmpappv2.data.local.database.PortfolioDatabase
import platform.Foundation.NSHomeDirectory

fun getPortfolioDatabaseBuilder(): RoomDatabase.Builder<PortfolioDatabase> {
    val dbFile = "${NSHomeDirectory()}/${PortfolioDatabase.DATABASE_NAME}"
    return Room.databaseBuilder<PortfolioDatabase>(
        name = dbFile
    )
}