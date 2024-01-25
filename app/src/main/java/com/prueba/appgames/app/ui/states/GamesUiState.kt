package com.prueba.appgames.app.ui.states

import com.prueba.appgames.app.data.network.Response.GamesResponse

sealed interface GameUiState{
    object Loading: GameUiState
    data class Error(val exception: Throwable): GameUiState
    data class Success(val data: GamesResponse) : GameUiState
}