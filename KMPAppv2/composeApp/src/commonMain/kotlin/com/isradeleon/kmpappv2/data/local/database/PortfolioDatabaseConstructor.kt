package com.isradeleon.kmpappv2.data.local.database

import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

/**
 * The RoomDatabaseConstructor is an interface from room
 * that abstracts database creation for each platform:
 * Android & iOS.
 */
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object PortfolioDatabaseConstructor: RoomDatabaseConstructor<PortfolioDatabase>

fun getPortfolioDatabase(
    builder: RoomDatabase.Builder<PortfolioDatabase>
): PortfolioDatabase {
    return builder
        //.addMigrations()
        //.fallbackToDestructiveMigrationOnDowngrade()
        /**
         * This bundled SQLite driver is an expect,
         * which represents different implementations
         * for each platform.
         */
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}