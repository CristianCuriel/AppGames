package com.prueba.appgames.app.data.network

import com.prueba.appgames.app.core.Constantes
import com.prueba.appgames.app.core.Constantes.API_KEY
import com.prueba.appgames.app.data.network.Response.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("games?")

    suspend fun doGames(
        @Query("key") key: String = API_KEY
    ): Response<GamesResponse>
}