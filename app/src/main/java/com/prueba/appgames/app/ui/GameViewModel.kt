package com.prueba.appgames.app.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.appgames.app.data.Models.listGamesModel
import com.prueba.appgames.app.domain.GameUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel() : ViewModel() {

    var getGamesUseCase = GameUseCase()

    private val _games = MutableStateFlow<List<listGamesModel>>(emptyList())
    val games = _games.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        onCreate()
    }

    fun onCreate() {
        viewModelScope.launch {
            getGamesUseCase().collectLatest { R ->
                when (R) {
                    is GameUiState.Error -> {
                        _showErrorToastChannel.send(true)
                        Log.i("cris","${R.message}")
                    }

                    is GameUiState.Success -> {
                        R.data?.let { G -> _games.update { G } }
                    }
                }
            }
        }
    }


}