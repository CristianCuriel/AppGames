package com.prueba.appgames.app.data.network

import com.prueba.appgames.app.core.network.RetrofitHelper
import com.prueba.appgames.app.data.Models.listGamesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getGames(): List<listGamesModel>{
        return withContext(Dispatchers.IO){
            val reponse = retrofit.create(RetrofitService::class.java).doGames()
            reponse.body()?.listGames!!
        }
    }
}