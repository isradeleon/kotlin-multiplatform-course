package com.isradeleon.kmpappv2.presentation.screens.coin_details_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CoinDetailsScreen(
    coinId: String
) {
    val coinDetailsViewModel = koinViewModel<CoinDetailsViewModel> {
        parametersOf(coinId)
    }
    val state by coinDetailsViewModel.state.collectAsStateWithLifecycle()

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Here will be the details")
    }
}

@Composable
fun CoinDetailsContent() {

}