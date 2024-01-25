package com.prueba.appgames.app.data.network

import com.prueba.appgames.app.core.Constantes.API_KEY
import com.prueba.appgames.app.data.network.Response.GameInfoResponse
import com.prueba.appgames.app.data.network.Response.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("games?")
    suspend fun doGames(
        @Query("key") key: String = API_KEY,
    ): Response<GamesResponse> //GamesResponse es el modelo de datos

    @GET("games?")
    suspend fun doMoreGames(
        @Query("key") key: String = API_KEY,
        @Query("page") page: Int
    ): Response<GamesResponse> //GamesResponse es el modelo de datos

    @GET("games/{id}?")
    suspend fun doInfoGame(
        @Query("key") key: String = API_KEY,
        @Path("id") idGame: Int
    ): Response<GameInfoResponse> //GamesResponse es el modelo de datos


}