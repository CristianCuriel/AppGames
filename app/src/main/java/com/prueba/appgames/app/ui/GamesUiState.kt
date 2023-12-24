package com.prueba.appgames.app.ui

import com.prueba.appgames.app.data.Models.listGamesModel

sealed interface GameUiState{
    object Loading: GameUiState
    data class Error(val exception: String?): GameUiState
    data class Success(val data: List<listGamesModel>) : GameUiState
}