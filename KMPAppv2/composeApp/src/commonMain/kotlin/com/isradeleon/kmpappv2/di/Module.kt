package com.isradeleon.kmpappv2.di

import androidx.room.RoomDatabase
import com.isradeleon.kmpappv2.common.network.HttpClientFactory
import com.isradeleon.kmpappv2.data.local.database.PortfolioDatabase
import com.isradeleon.kmpappv2.data.local.database.getPortfolioDatabase
import com.isradeleon.kmpappv2.data.remote.api.KtorCoinsRemoteDataSource
import com.isradeleon.kmpappv2.data.remote.src.CoinsRemoteDataSource
import com.isradeleon.kmpappv2.domain.use_cases.GetCoinDetailUseCase
import com.isradeleon.kmpappv2.domain.use_cases.GetCoinsUseCase
import com.isradeleon.kmpappv2.domain.use_cases.GetPriceHistoryUseCase
import com.isradeleon.kmpappv2.presentation.coins_list_screen.CoinsListViewModel
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    config: KoinAppDeclaration? = null
) = startKoin {
    config?.invoke(this)

    /**
     * Here we provide all the common + platform modules.
     */
    modules(
        platformModule,
        sharedModule
    )
}

/**
 * "Expect" keyword declares a platform specific API in the "common" code.
 * This is provided with the "actual" keyword in each platform's module (iOS & Android).
 */
expect val platformModule: Module

/**
 * Shared dependencies in the common main module.
 * You could make multiple modules to better organize
 * dependencies. For this practice app, one shared module is fine.
 */
val sharedModule = module {
    /**
     * SINGLE comes from Koin library.
     * It specifies a SINGLETON instance being injected.
     */
    single<HttpClient> {
        /**
         * This get param comes from Koin.
         * It tells Koin to RESOLVE and inject the dependency (figure it out)
         * based on the type. In this case it will retrieve the PLATFORM
         * specific HTTP client engine.
         *
         * We have to provide this engine in the respective platform modules:
         *
         * androidMain & iosMain (NOT the iosApp, which is the Xcode project).
         */
        HttpClientFactory.create(get())
    }

    /**
     * ViewModels
     */
    viewModel { CoinsListViewModel(get()) }

    /**
     * Use cases
     */
    factory { GetCoinsUseCase(get()) }
    factory { GetCoinDetailUseCase(get()) }
    factory { GetPriceHistoryUseCase(get()) }

    /**
     * Data sources
     */
    single<CoinsRemoteDataSource> { KtorCoinsRemoteDataSource(get()) }

    /**
     * Database
     */
    single<PortfolioDatabase> {
        getPortfolioDatabase(
            get<RoomDatabase.Builder<PortfolioDatabase>>()
        )
    }
}