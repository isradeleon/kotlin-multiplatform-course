package com.isradeleon.kmpappv2.di

import androidx.room.RoomDatabase
import com.isradeleon.kmpappv2.data.local.database.PortfolioDatabase
import com.isradeleon.kmpappv2.database.getPortfolioDatabaseBuilder
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

/**
 * iOS "actual" platform specific implementation for HTTP.
 */
actual val platformModule = module {
    single<HttpClientEngine> {
        /**
         * This method from Ktor
         * creates an HttpClientEngine
         * for iOS.
         */
        Darwin.create()
    }

    single<RoomDatabase.Builder<PortfolioDatabase>> {
        getPortfolioDatabaseBuilder()
    }
}