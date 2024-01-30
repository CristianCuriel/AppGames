package com.prueba.appgames.app.data

import com.prueba.appgames.app.data.network.GameService
import com.prueba.appgames.app.ui.states.GameUiState
import com.prueba.appgames.app.ui.states.InfoGameUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GamesRepository {

    private val api = GameService()


    suspend fun getMoreGames(nextPage:Int): Flow<GameUiState> {
        return flow {
            try {
                val response = api.getMoreGames(nextPage)
                emit(GameUiState.Success(response))
            } catch (e:Throwable) {
                emit(GameUiState.Error(e))
            }
        }
    }

    suspend fun getInfoGame(idGame:Int): Flow<InfoGameUiState> {
        return flow {
            try {
                val response = api.getInfoGame(idGame)
                emit(InfoGameUiState.Success(response))
            } catch (e:Throwable) {
                emit(InfoGameUiState.Error(e))
            }
        }
    }

}
