package com.prueba.appgames.app.domain

import com.prueba.appgames.app.data.GamesRepository
import com.prueba.appgames.app.data.Models.listGamesModel
import com.prueba.appgames.app.data.network.Response.GamesResponse

class GameUseCase {

    private val repository = GamesRepository()

    suspend operator fun  invoke() : List<listGamesModel> = repository.getGames()
}