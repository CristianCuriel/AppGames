package com.prueba.appgames.app.domain

import com.prueba.appgames.app.data.GamesRepository
import com.prueba.appgames.app.ui.states.GameUiState
import com.prueba.appgames.app.ui.states.InfoGameUiState
import kotlinx.coroutines.flow.Flow

class GameUseCase {

    private val repository = GamesRepository()

    suspend fun getMoreGamesCaseUse(nextPage:Int = 1): Flow<GameUiState> = repository.getMoreGames(nextPage)

    suspend fun getInfoGameCaseUse(idGame:Int): Flow<InfoGameUiState> = repository.getInfoGame(idGame)
}