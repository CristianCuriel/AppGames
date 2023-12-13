package com.prueba.appgames.app.data

import com.prueba.appgames.app.data.Models.listGamesModel
import com.prueba.appgames.app.data.network.GameService
import com.prueba.appgames.app.data.network.Response.GamesResponse

class GamesRepository {

    private val api = GameService()

    suspend fun getGames(): List<listGamesModel>{
        val response = api.getGames()
        return response
    }
}