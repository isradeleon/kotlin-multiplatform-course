package com.isradeleon.kmpappv2.presentation.screens.coins_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isradeleon.kmpappv2.common.Outcome
import com.isradeleon.kmpappv2.common.utils.toStringResource
import com.isradeleon.kmpappv2.domain.use_cases.GetCoinsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinsListViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {
    // Implemented StateFlow, which stores the last state.
    private val _state = MutableStateFlow(CoinsListState())
    val state = _state

    init {
        viewModelScope.launch {
            getCoinsList()
        }
    }

    private suspend fun getCoinsList() {
        when(val coinsResponse = getCoinsUseCase.execute()) {
            is Outcome.Success -> {
                _state.update {
                    CoinsListState(
                        coins = coinsResponse.data,
                        isLoading = false
                    )
                }
            }

            is Outcome.Failure -> {
                _state.update {
                    CoinsListState(
                        error = coinsResponse.error.toStringResource(),
                        isLoading = false
                    )
                }
            }
        }
    }
}