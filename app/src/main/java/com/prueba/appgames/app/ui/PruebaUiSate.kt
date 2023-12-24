package com.prueba.appgames.app.ui

import com.prueba.appgames.app.data.Models.listGamesModel

sealed interface PruebaUiSate{
    object Loading:PruebaUiSate
    data class Error(val exception: Throwable): PruebaUiSate
    data class Success(val data: listGamesModel) : PruebaUiSate
}