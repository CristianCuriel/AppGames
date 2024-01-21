package com.prueba.appgames.app.data.network

import com.prueba.appgames.app.core.network.RetrofitHelper
import com.prueba.appgames.app.data.network.Response.GamesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getMoreGames(nextPage: Int): GamesResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(RetrofitService::class.java).doMoreGames(page = nextPage)
            response.body()!!
        }
    }

}

