package com.prueba.appgames.app.domain

import com.prueba.appgames.app.data.GamesRepository
import com.prueba.appgames.app.ui.GameUiState
import kotlinx.coroutines.flow.Flow

class GameUseCase {

    private val repository = GamesRepository()

    suspend fun getMoreGamesCaseUse(nextPage:Int): Flow<GameUiState> = repository.getMoreGames(nextPage)
}