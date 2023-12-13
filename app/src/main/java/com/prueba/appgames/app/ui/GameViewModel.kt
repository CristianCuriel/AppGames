package com.prueba.appgames.app.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.appgames.app.data.Models.listGamesModel
import com.prueba.appgames.app.domain.GameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel(): ViewModel() {

    var getGamesUseCase = GameUseCase()

    private val _games = MutableStateFlow<List<listGamesModel>>(emptyList())
    val games = _games.asStateFlow()

    init {
        onCreate()
    }

    fun onCreate(){
        viewModelScope.launch {
            val result = getGamesUseCase()
            _games.value = result
            Log.i("cris","$result")
        }
    }



}