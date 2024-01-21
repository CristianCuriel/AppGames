package com.prueba.appgames.app.data

import com.prueba.appgames.app.data.network.GameService
import com.prueba.appgames.app.ui.GameUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class GamesRepository {

    private val api = GameService()

    suspend fun getGames(): Flow<GameUiState> {
        return flow {
            emit(GameUiState.Loading)
            try {
                val response = api.getGames()
                emit(GameUiState.Success(response))
            } catch (e: IOException) {
                emit(GameUiState.Error(e.cause!!))
            } catch (e: HttpException) {
                emit(GameUiState.Error(e.cause!!))
            } catch (e: Exception) {
                emit(GameUiState.Error(e.cause!!))
            }
        }
    }

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

}
