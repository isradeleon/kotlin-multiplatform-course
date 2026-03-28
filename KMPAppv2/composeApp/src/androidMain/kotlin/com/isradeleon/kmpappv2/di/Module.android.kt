package com.isradeleon.kmpappv2.di

import androidx.room.RoomDatabase
import com.isradeleon.kmpappv2.data.local.database.PortfolioDatabase
import com.isradeleon.kmpappv2.database.getPortfolioDatabaseBuilder
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.dsl.module

/**
 * The "actual" keyword is used to define the
 * platform specific implementation for an "expect"
 * declaration.
 */
actual val platformModule = module {
    single<HttpClientEngine> {
        /**
         * This method creates an HttpClientEngine
         * for Ktor based on OkHttp.
         */
        Android.create()
    }

    single<RoomDatabase.Builder<PortfolioDatabase>> {
        getPortfolioDatabaseBuilder(get())
    }
}