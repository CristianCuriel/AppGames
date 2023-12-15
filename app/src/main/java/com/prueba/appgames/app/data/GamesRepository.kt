package com.prueba.appgames.app.data

import com.prueba.appgames.app.data.Models.listGamesModel
import com.prueba.appgames.app.data.network.GameService
import com.prueba.appgames.app.ui.GameUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class GamesRepository {

    private val api = GameService()

    suspend fun getGames(): Flow<GameUiState<List<listGamesModel>>> {
        //val response = api.getGames()
        return flow {
            val response = try {
                api.getGames()

            } catch (e: IOException) {
                e.printStackTrace()
                emit(GameUiState.Error(message = "${e.message}"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(GameUiState.Error(message = "${e.message}"))
                return@flow
            }  catch (e: Exception) {
                e.printStackTrace()
                emit(GameUiState.Error(message = "Error loading products"))
                return@flow
            }

            emit(GameUiState.Success(response))
        }
    }
}