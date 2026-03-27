package com.isradeleon.kmpappv2.presentation.coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isradeleon.kmpappv2.common.Response
import com.isradeleon.kmpappv2.domain.GetCoinsUseCase
import com.isradeleon.kmpappv2.presentation.state.CoinsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CoinsListViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {
    // Implemented StateFlow, which stores the last state.
    private val _state = MutableStateFlow(CoinsState())
    val state = _state
        // Replacement for the init VM function
        .onStart { // Called when someone begins collecting the state flow.
            getCoinsList()
        }.stateIn( // Converts the flow into a state flow, viewModel-scoped.
            scope = viewModelScope,
            /**
             * This indicates that after the last subscriber stops collecting,
             * the flow will remain active for another 5000 millis.
             *
             * This prevents triggering unnecessary API calls, until that
             * 5 seconds window (for example during a configuration change).
             */
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CoinsState()
        )

    private suspend fun getCoinsList() {
        when(val coinsResponse = getCoinsUseCase.execute()) {
            is Response.Fail -> {
                _state.update {
                    CoinsState(
                        error = null // TODO: Handle error message.
                    )
                }
            }
            is Response.Success -> {
                _state.update {
                    CoinsState(
                        coins = coinsResponse.data
                    )
                }
            }
        }
    }
}