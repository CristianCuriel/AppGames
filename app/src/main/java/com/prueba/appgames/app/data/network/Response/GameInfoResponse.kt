package com.prueba.appgames.app.data.network.Response

import com.prueba.appgames.app.data.Models.infoGame.AddedByStatus
import com.prueba.appgames.app.data.Models.infoGame.Developer
import com.prueba.appgames.app.data.Models.infoGame.EsrbRating
import com.prueba.appgames.app.data.Models.infoGame.Genre
import com.prueba.appgames.app.data.Models.infoGame.MetacriticPlatform
import com.prueba.appgames.app.data.Models.infoGame.ParentPlatform
import com.prueba.appgames.app.data.Models.infoGame.PlatformXX
import com.prueba.appgames.app.data.Models.infoGame.Publisher
import com.prueba.appgames.app.data.Models.infoGame.Rating
import com.prueba.appgames.app.data.Models.infoGame.Reactions
import com.prueba.appgames.app.data.Models.infoGame.Store
import com.prueba.appgames.app.data.Models.infoGame.Tag

data class GameInfoResponse(
    val achievements_count: Int,
    val added: Int,
    val added_by_status: AddedByStatus,
    val additions_count: Int,
    val alternative_names: List<Any>,
    val background_image: String,
    val background_image_additional: String,
    val clip: Any,
    val creators_count: Int,
    val description: String,
    val description_raw: String,
    val developers: List<Developer>,
    val dominant_color: String,
    val esrb_rating: EsrbRating,
    val game_series_count: Int,
    val genres: List<Genre>,
    val id: Int,
    val metacritic: Int,
    val metacritic_platforms: List<MetacriticPlatform>,
    val metacritic_url: String,
    val movies_count: Int,
    val name: String,
    val name_original: String,
    val parent_achievements_count: Int,
    val parent_platforms: List<ParentPlatform>,
    val parents_count: Int,
    val platforms: List<PlatformXX>,
    val playtime: Int,
    val publishers: List<Publisher>,
    val rating: Double,
    val rating_top: Int,
    val ratings: List<Rating>,
    val ratings_count: Int,
    val reactions: Reactions,
    val reddit_count: Int,
    val reddit_description: String,
    val reddit_logo: String,
    val reddit_name: String,
    val reddit_url: String,
    val released: String,
    val reviews_count: Int,
    val reviews_text_count: Int,
    val saturated_color: String,
    val screenshots_count: Int,
    val slug: String,
    val stores: List<Store>,
    val suggestions_count: Int,
    val tags: List<Tag>,
    val tba: Boolean,
    val twitch_count: Int,
    val updated: String,
    val user_game: Any,
    val website: String,
    val youtube_count: Int
)