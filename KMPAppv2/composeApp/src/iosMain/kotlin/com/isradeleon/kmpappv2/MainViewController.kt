package com.isradeleon.kmpappv2

import androidx.compose.ui.window.ComposeUIViewController
import com.isradeleon.kmpappv2.di.initKoin

fun MainViewController() = ComposeUIViewController(
    /**
     * This "configure" callback is called
     * as soon as the iOS app starts.
     */
    configure = {
        /**
         * Initializing Koin DI.
         */
        initKoin()
    }
) { App() }