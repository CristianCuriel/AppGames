package com.prueba.appgames.app.data.network.Response

import com.google.gson.annotations.SerializedName
import com.prueba.appgames.app.data.Models.listGamesModel

data class GamesResponse(
    @SerializedName("count")
    val count:Int,
    @SerializedName("results")
    val listGames: List<listGamesModel>
)