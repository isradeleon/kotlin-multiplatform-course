package com.isradeleon.kmpappv2.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.isradeleon.kmpappv2.data.local.database.PortfolioDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun getPortfolioDatabaseBuilder(): RoomDatabase.Builder<PortfolioDatabase> {
    val dbFile = "${documentDirectory()}/${PortfolioDatabase.DATABASE_NAME}"
    return Room.databaseBuilder<PortfolioDatabase>(
        name = dbFile
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}