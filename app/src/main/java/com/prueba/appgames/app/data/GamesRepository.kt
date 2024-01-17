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
        //val response = api.getGames()
        return flow {
            emit(GameUiState.Loading)
            try {
                val response = api.getGames()
                emit(GameUiState.Success(response))
            } catch (e: IOException) {
                emit(GameUiState.Error(e.message))
            } catch (e: HttpException) {
                emit(GameUiState.Error(e.message ?: "Error de HTTP"))
            } catch (e: Exception) {
                emit(GameUiState.Error(e.message ?: "Error desconocido"))
            }
        }
    }

    suspend fun getMoreGames(nextPage:Int): Flow<GameUiState> {
        return flow {

            try {
                val response = api.getMoreGames(nextPage)
                emit(GameUiState.Success(response))
            } catch (e: IOException) {
                emit(GameUiState.Error(e.message))
            } catch (e: HttpException) {
                emit(GameUiState.Error(e.message ?: "Error de HTTP al cargar mas juegos"))
            } catch (e: Exception) {
                emit(GameUiState.Error(e.message ?: "Error desconocido al cargar mas juegos"))
            }
        }
    }
}
