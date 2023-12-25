package com.prueba.appgames.app.data.Models

import com.google.gson.annotations.SerializedName

data class listGamesModel(

    @SerializedName("background_image")
    val background_image: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("released")
    val released: String,
    val short_screenshots: List<ShortScreenshot>,
    @SerializedName("stores")
    val tiendasCompra: List<Store>,
    @SerializedName("tags")
    val tags: List<Tag>,
    val updated: String,
    @SerializedName("metacritic")
    val metacritic: Int,

    )