package com.isradeleon.kmpappv2

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.isradeleon.kmpappv2.presentation.coins.CoinsListScreen
import com.isradeleon.kmpappv2.theme.KMPAppV2Theme

@Composable
@Preview
fun App() {
    KMPAppV2Theme {
        Scaffold { padding ->
            CoinsListScreen(
                modifier = Modifier.padding(padding)
            ) {

            }
        }
    }
}