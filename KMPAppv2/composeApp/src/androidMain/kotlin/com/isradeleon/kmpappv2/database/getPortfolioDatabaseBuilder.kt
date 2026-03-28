package com.isradeleon.kmpappv2.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.isradeleon.kmpappv2.data.local.database.PortfolioDatabase

fun getPortfolioDatabaseBuilder(
    context: Context
): RoomDatabase.Builder<PortfolioDatabase> {
    val dbFile = context.getDatabasePath(PortfolioDatabase.DATABASE_NAME)
    return Room.databaseBuilder(
        context = context,
        name = dbFile.absolutePath
    )
}