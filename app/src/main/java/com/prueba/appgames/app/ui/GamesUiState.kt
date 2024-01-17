package com.prueba.appgames.app.ui

import com.prueba.appgames.app.data.network.Response.GamesResponse

sealed interface GameUiState{
    object Loading: GameUiState
    data class Error(val exception: String?): GameUiState
    data class Success(val data: GamesResponse) : GameUiState
}