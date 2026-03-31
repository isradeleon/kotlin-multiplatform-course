package com.isradeleon.kmpappv2.presentation.screens.coin_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isradeleon.kmpappv2.common.Outcome
import com.isradeleon.kmpappv2.domain.use_cases.AddToFavoritesUseCase
import com.isradeleon.kmpappv2.domain.use_cases.GetCoinDetailUseCase
import com.isradeleon.kmpappv2.domain.use_cases.GetPriceHistoryUseCase
import com.isradeleon.kmpappv2.domain.use_cases.ObserveFavoriteByIdUseCase
import com.isradeleon.kmpappv2.domain.use_cases.RemoveFavoriteUseCase
import kmpappv2.composeapp.generated.resources.Res
import kmpappv2.composeapp.generated.resources.error_unknown
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinDetailsViewModel(
    private val getCoinDetailsUseCase: GetCoinDetailUseCase,
    private val getPriceHistoryUseCase: GetPriceHistoryUseCase,
    private val observeFavoriteByIdUseCase: ObserveFavoriteByIdUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val coinId: String
): ViewModel() {
    private val _state = MutableStateFlow(CoinDetailsState())
    val state = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllCoinData()
        }
    }

    fun observeFavorite(id: String) = observeFavoriteByIdUseCase.execute(id)

    fun addToFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value.coin?.let {
                addToFavoritesUseCase.execute(it)
            }
        }
    }

    fun removeFromFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            removeFavoriteUseCase.execute(coinId)
        }
    }

    private suspend fun getAllCoinData() {
        combine(
            getCoinDetails(),
            getPriceHistory(),
        ) { coinDetails, priceHistory ->
            if (coinDetails is Outcome.Success && priceHistory is Outcome.Success) {
                _state.update {
                    CoinDetailsState(
                        coin = coinDetails.data,
                        priceHistory = priceHistory.data,
                        isLoading = false
                    )
                }
            } else {
                _state.update {
                    CoinDetailsState(
                        error = Res.string.error_unknown,
                        isLoading = false
                    )
                }
            }
        }.collect()
    }

    private fun getCoinDetails() = flow {
        emit(getCoinDetailsUseCase.execute(coinId))
    }

    private fun getPriceHistory() = flow {
        emit(getPriceHistoryUseCase.execute(coinId))
    }
}