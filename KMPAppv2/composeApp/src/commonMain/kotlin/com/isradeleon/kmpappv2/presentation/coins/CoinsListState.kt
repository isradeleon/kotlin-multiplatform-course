package com.isradeleon.kmpappv2.presentation.coins

import androidx.compose.runtime.Stable
import com.isradeleon.kmpappv2.domain.model.Coin
import org.jetbrains.compose.resources.StringResource

/**
 * Stable helps to optimize recomposition.
 * This means that properties won't change unexpectedly.
 */
@Stable
data class CoinsListState(
    val error: StringResource? = null,
    val coins: List<Coin> = emptyList()
)