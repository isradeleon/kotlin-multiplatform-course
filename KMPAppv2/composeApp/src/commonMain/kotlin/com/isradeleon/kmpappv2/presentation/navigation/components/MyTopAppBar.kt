package com.isradeleon.kmpappv2.presentation.navigation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import kmpappv2.composeapp.generated.resources.Res
import kmpappv2.composeapp.generated.resources.arrow_back_24dp
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    isHome: Boolean,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "KMP Crypto",
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            if (!isHome) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painterResource(Res.drawable.arrow_back_24dp),
                        contentDescription = "Back action"
                    )
                }
            }
        }
    )
}