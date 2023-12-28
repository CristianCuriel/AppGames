package com.prueba.appgames.app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.appgames.app.domain.GameUseCase
import com.prueba.appgames.app.ui.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel() : ViewModel() {

    var getGamesUseCase = GameUseCase()

    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Loading)
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private val _showDialog = MutableLiveData<Boolean>(false)
    val showDialog: LiveData<Boolean> = _showDialog

    init {
        onCreate()
    }

    fun onshowDialog() {
        _showDialog.value = !_showDialog.value!!
    }

    private fun onCreate() {
        viewModelScope.launch {
            getGamesUseCase().collect { state ->
                _uiState.value = state
            }
        }
    }


}