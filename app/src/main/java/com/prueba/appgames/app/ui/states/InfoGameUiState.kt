package com.prueba.appgames.app.ui.states

import com.prueba.appgames.app.data.network.Response.GameInfoResponse

sealed interface InfoGameUiState {
    object Loading: InfoGameUiState
    data class Error(val exception: Throwable): InfoGameUiState
    data class Success(val data: GameInfoResponse) : InfoGameUiState
}